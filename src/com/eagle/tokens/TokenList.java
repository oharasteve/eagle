// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 23, 2010

package com.eagle.tokens;

import java.util.ArrayList;

import com.eagle.programmar.EagleSyntax;

public class TokenList<T extends AbstractToken> extends AbstractToken
{
	public ArrayList<T> _elements = new ArrayList<T>();
	
	public boolean addToken(T token)
	{
		return _elements.add(token);
	}
	
	public boolean addToken(T token, AbstractToken startToken)
	{
		return addToken(token, startToken, startToken);
	}
	
	@SuppressWarnings("unchecked")
	public boolean addTokenUnchecked(AbstractToken token, AbstractToken startToken)
	{
		return addToken((T) token, startToken);
	}
	
	public boolean addToken(T token, AbstractToken startToken, AbstractToken endToken)
	{
		if (startToken != null)
		{
			token._fileName = startToken._fileName;
			token._currentChar = startToken._currentChar;
			token._currentLine = startToken._currentLine;
		}
		
		if (endToken != null)
		{
			token._endChar = endToken._endChar;
			token._endLine = endToken._endLine;
		}

		return _elements.add(token);
	}
	
	public T set(int indx, T token)
	{
		return _elements.set(indx, token);
	}
	
	@SuppressWarnings("unchecked")
	public T setUnchecked(int indx, AbstractToken token)
	{
		return set(indx, (T) token);
	}
	
	public void insert(int index, T token)
	{
		_elements.add(index, token);
	}
	
	@SuppressWarnings("unchecked")
	public void insertUnchecked(int index, AbstractToken token)
	{
		insert(index, (T) token);
	}
	
	public T first()
	{
		return _elements.get(0);
	}
	
	public int size()
	{
		return _elements.size();
	}
	
	@Override
	public void forceSyntax(EagleSyntax syntax) throws IllegalAccessException
	{
		setSyntax(syntax);
		for (AbstractToken token : _elements)
		{
			token.forceSyntax(syntax);
		}
	}
}
