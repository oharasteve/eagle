// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 28, 2015

package com.eagle.preprocess;

import java.util.TreeMap;

import com.eagle.math.EagleValue;

public class EagleSymbolTable
{
	private TreeMap<String, EagleValue> _symbolTable = new TreeMap<String, EagleValue>();
	
	public boolean isDefined(String symbol)
	{
		return _symbolTable.containsKey(symbol);
	}
	
	public void setSymbol(String symbol, EagleValue value)
	{
		//System.out.println("***** Adding symbol " + symbol);
		removeSymbol(symbol);	// Usually doesn't already have a definition
		_symbolTable.put(symbol, value);
	}
	
	public EagleValue findSymbol(String symbol)
	{
		if (! _symbolTable.containsKey(symbol))
		{
			//System.out.println("***** Didn't find symbol " + symbol);
			return null;
		}
		//System.out.println("***** Found symbol " + symbol);
		return _symbolTable.get(symbol);
	}
	
	public void removeSymbol(String symbol)
	{
		if (_symbolTable.containsKey(symbol))
		{
			_symbolTable.remove(symbol);
		}
	}
}
