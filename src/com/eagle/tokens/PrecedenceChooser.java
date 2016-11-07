// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Jeff Wilkinson, Nov 1, 2011

package com.eagle.tokens;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.TreeMap;

//
// Trying to keep the inner classes in order. The reflection method getClosses() return them all
// in alphabetical order. getFields() returns in the order defined. Oh well. The name now has a
// number in it to (1) keep them in order, (2) make them easier to find in the source code, and
// (3) force the correct order. AWK_1xx_name means it is a primary operator, like negation.
// AWK_2xx_name means it is a binary or tertiary operator like addition of if-then-else ( ? : ).
//

public abstract class PrecedenceChooser extends TokenChooser
{
	private PrecedenceOperator.AllowedPrecedence _allowed = PrecedenceOperator.AllowedPrecedence.ANY;
	private Class<? extends PrecedenceOperator> _lastChoice = null;
	
	public static class OperatorList
	{
		public TreeMap<Integer, Class<? extends AbstractToken>> _list = null;
	}
	
	private OperatorList _operatorList;

	public abstract static class PrimaryOperator extends TokenSequence
	{
		// Nothing to add -- just a layer
	}

	public abstract static class PrecedenceOperator extends TokenSequence
	{
		public enum AllowedPrecedence 
		{
			ANY,
			HIGHER,
			ATLEAST
		}
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface P
	{
		// @P(#) means precedence order. The actual number is only used for sorting.
		// By convention, use @P(10) @P(20) for terminal tokens, and @P(100) @P(110) for the rest.
		// Binary (and tertiary) operators must follow all the unary (and no-ary) operators.
		int value();
	}

	public PrecedenceChooser(OperatorList operators) 
	{
		setOperators(operators);
	}
	
	public PrecedenceChooser(OperatorList operators,
			PrecedenceOperator.AllowedPrecedence allowed,
			Class<? extends PrecedenceOperator> lastChoice)
	{ 
		setOperators(operators);
		_allowed = allowed;
		_lastChoice = lastChoice;
	}
	
	public void setOperators(OperatorList operators)
	{
		_operatorList = operators;
		if (operators._list != null) return;
		_operatorList._list = new TreeMap<Integer, Class<? extends AbstractToken>>();
		
		Class<? extends AbstractToken> cls = this.getClass();
				
		for (Field fld : cls.getFields())
		{
			@SuppressWarnings("unchecked")
			Class<? extends AbstractToken> fldType = (Class<? extends AbstractToken>) fld.getType();
			if (TerminalToken.class.isAssignableFrom(fldType))
			{
				Integer precedence = fld.getAnnotation(P.class).value();
				_operatorList._list.put(precedence, fldType);
				//System.out.println("*** Adding TerminalToken " + fldType.getCanonicalName());
			}
		}
		
		for (Class<?> innerClass : cls.getClasses())
		{
			if (PrimaryOperator.class.isAssignableFrom(innerClass) || PrecedenceOperator.class.isAssignableFrom(innerClass))
			{
				@SuppressWarnings("unchecked")
				Class<? extends AbstractToken> precedenceClass = (Class<? extends AbstractToken>) innerClass;
				if ((precedenceClass.getModifiers() & Modifier.ABSTRACT) == 0)
				{
					Integer precedence = precedenceClass.getAnnotation(P.class).value();
					_operatorList._list.put(precedence, precedenceClass);
					//System.out.println("*** Adding PrecedenceOperator " + binaryClass.getCanonicalName());
				}
			}
		}
	}
	
	public Collection<Class<? extends AbstractToken>> getOperators()
	{
		return _operatorList._list.values();
	}
	
	public Class<? extends PrecedenceOperator> getLastChoice()
	{
		return _lastChoice;
	}
	
	public PrecedenceOperator.AllowedPrecedence getAllowed()
	{
		return _allowed;
	}
}
