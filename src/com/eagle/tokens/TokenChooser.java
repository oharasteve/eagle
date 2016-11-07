// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

package com.eagle.tokens;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

import com.eagle.programmar.EagleSyntax;

public class TokenChooser extends AbstractToken
{
	private static HashMap<Class<? extends AbstractToken>, HashSet<Class<? extends AbstractToken>>> _allTokenChoosers =
			new HashMap<Class<? extends AbstractToken>, HashSet<Class<? extends AbstractToken>>>();

	public static final String WHICH_TOKEN = "_whichToken";
	protected @SKIP AbstractToken _whichToken = null;

	@Retention(RetentionPolicy.RUNTIME)
	public @interface FIRST
	{
		// @FIRST means look for this before the others
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CHOICE
	{
		// @CHOICE means look for this in whatever order
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface LAST
	{
		// @LAST means look for this after the others
	}
	
	public void setWhich(AbstractToken token)
	{
		if (token != null) validateChoice(token);
		_whichToken = token;
		_present = token != null;
	}
	
	private void validateChoice(AbstractToken token)
	{
		Class<? extends TokenChooser> myClass = this.getClass();
		Class<? extends AbstractToken> tokenClass = token.getClass();
		
		// Build the list of available choices if needed
		if (! _allTokenChoosers.containsKey(myClass))
		{
			buildChoices(myClass);
		}
		
		// See if this token choice is allowed
		HashSet<Class<? extends AbstractToken>> choices = _allTokenChoosers.get(myClass);
		if (choices.contains(tokenClass)) return;
		
		// Oh, well maybe it extends one of the classes
		for (Class<? extends AbstractToken> choice : choices)
		{
			if (choice.isAssignableFrom(tokenClass)) return;
		}
		
		throw new RuntimeException("Cannot setWhich an instance of " + myClass.getCanonicalName() + " to " + tokenClass.getCanonicalName());
	}
	
	private static void buildChoices(Class<? extends TokenChooser> myClass)
	{
		HashSet<Class<? extends AbstractToken>> choices = new HashSet<Class<? extends AbstractToken>>();
		for (Field fld : myClass.getFields())
		{
			Class<?> fieldType = fld.getType();
			if (AbstractToken.class.isAssignableFrom(fieldType))
			{
				@SuppressWarnings("unchecked")
				Class<? extends AbstractToken> fieldClass = (Class<? extends AbstractToken>) fieldType;
				choices.add(fieldClass);
			}
		}
		for (Class<?> cls : myClass.getClasses())
		{
			if (AbstractToken.class.isAssignableFrom(cls))
			{
				@SuppressWarnings("unchecked")
				Class<? extends AbstractToken> classClass = (Class<? extends AbstractToken>) cls;
				choices.add(classClass);
			}
		}
		_allTokenChoosers.put(myClass, choices);
	}

	public AbstractToken getWhich()
	{
		return _whichToken;
	}
	
	@Override
	public void forceSyntax(EagleSyntax syntax) throws IllegalAccessException
	{
		setSyntax(syntax);
		if (_whichToken != null)
		{
			_whichToken.forceSyntax(syntax);
		}
	}
}
