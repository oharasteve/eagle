// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 21, 2013

// This does NOT work at all. Gets some kind of nasty java.lang.InstantiationException

package com.eagle.tokens;

import java.lang.reflect.Constructor;

// Think of this as "T { P T }*", such as "1, 211, 3, 18" where T=number P=comma

/*
 * Darn thing still doesn't work. See Java_Variable for an example (commented out of course).
 * I wish I could remove clsT and clsP from the constructor.
 */

public class SeparatedList<T extends AbstractToken, P extends TerminalPunctuationToken> extends TokenSequence
{
	public T first;
	public @OPT TokenList<TokenPair> rest;
	
	private char _punct;
	private Class<T> _clsT;
	private Class<P> _clsP;
	
	public SeparatedList(Class<T> clsT, Class<P> clsP, char punct)
	{
		_clsT = clsT;
		_clsP = clsP;
		_punct = punct;

		first = newElement();
	}
	
	public class TokenPair extends TokenSequence
	{
		public P separator;
		public T next;
		
		public TokenPair()
		{
			separator = newSeparator();
			next = newElement();
		}
	}
	
	T newElement()
	{
		try
		{
			Constructor<T> cons = _clsT.getConstructor();
			return cons.newInstance();
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Unable to create an instance of element", ex);
		}
	}

	P newSeparator()
	{
		try
		{
			Constructor<P> cons = _clsP.getConstructor();
			return cons.newInstance(_punct);
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Unable to create an instance of separator", ex);
		}
	}

}
