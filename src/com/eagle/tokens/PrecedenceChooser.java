// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Jeff Wilkinson, Nov 1, 2011

package com.eagle.tokens;

import java.util.ArrayList;


public abstract class PrecedenceChooser extends TokenChooser
{
	private ArrayList<Class<? extends AbstractToken>> _unaryChoices =
			new ArrayList<Class<? extends AbstractToken>>();
	private ArrayList<Class<? extends AbstractToken>> _binaryChoices =
			new ArrayList<Class<? extends AbstractToken>>();

	private PrecedenceOperator.AllowedPrecedence _allowed = PrecedenceOperator.AllowedPrecedence.ANY;
	private Class<? extends PrecedenceOperator> _lastChoice = null;
	
	protected abstract void establishChoices();		

	public static class ExpressionTerm extends TokenSequence
	{
		// Nothing to add -- just a layer
	}

	public static class PrecedenceOperator extends TokenSequence
	{
		public enum AllowedPrecedence 
		{
			ANY,
			HIGHER,
			ATLEAST
		}
	}
	
	public PrecedenceChooser() 
	{
		establishChoices();
	}
	
	public PrecedenceChooser(PrecedenceOperator.AllowedPrecedence allowed,
			Class<? extends PrecedenceOperator> lastChoice)
	{ 
		_allowed = allowed;
		_lastChoice = lastChoice;
		establishChoices();
	}
	
	protected void addTerm(Class<? extends AbstractToken> cls)
	{
		_unaryChoices.add(cls);
	}
		
	protected void addOperator(Class<? extends PrecedenceOperator> cls)
	{
		_binaryChoices.add(cls);
	}
		
	public ArrayList<Class<? extends AbstractToken>> getPrimaries()
	{
		return _unaryChoices;
	}
	
	public ArrayList<Class<? extends AbstractToken>> getBinaries()
	{
		return _binaryChoices;
	}
	
	public Class<? extends PrecedenceOperator> getLastChoice()
	{
		return _lastChoice;
	}
	
	public PrecedenceOperator.AllowedPrecedence getAllowed()
	{
		return _allowed;
	}
}	// end of PrecedenceChooser class
