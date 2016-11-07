// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 21, 2013

// This does NOT work at all. Gets some kind of nasty java.lang.InstantiationException

package com.eagle.tokens;

// Think of this as "T { P T }*", such as "1, 211, 3, 18" where T=number P=comma

/**
 * @param <T> Token class
 * @param <P> Punctuation class, like a comma
 */
public class SeparatedList<T extends AbstractToken, P extends AbstractToken> extends TokenList<AbstractToken>
{
	public int getPrimaryCount()
	{
		return (size()+1) / 2;	// 5 total elements means 3 primary elements
	}

	public int getSecondaryCount()
	{
		return (size()-1) / 2;	// 5 total elements means 2 primary elements
	}
	
	@SuppressWarnings("unchecked")
	public T getPrimaryElement(int index)
	{
		int i = 2 * index;	// third element, index=2 is actually fifth, i=4
		return (T) _elements.get(i);
	}
	
	@SuppressWarnings("unchecked")
	public P getSecondaryElement(int index)
	{
		int i = 2 * index + 1;		// third element. index=2 is actually sixth, i=5
		return (P) _elements.get(i);
	}

	public void addPrimaryElement(T element)
	{
		_elements.add(element);
	}
	
	public void addSecondaryElement(P element)
	{
		_elements.add(element);
	}
}
