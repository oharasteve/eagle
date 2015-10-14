// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2015

package com.eagle.preprocess;

import java.util.HashMap;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;

public abstract class EagleInclude
{
	protected EagleFileReader _oldLines;
	
	// Symbol table (macro names)
	public HashMap<String, AbstractToken> _symbolTable;
	
	// Macros read from one EagleFileReader and collects lines into another
	protected EagleFileReader _newLines = new EagleFileReader();
	
	public EagleInclude(HashMap<String, AbstractToken> symbolTable)
	{
		_symbolTable = symbolTable;
	}
	
	// Each language is a little different
	public abstract EagleFileReader preprocessFile(EagleFileReader lines, FindIncludeFile findInclude);
	
	// Copy an element that is not a macro
	// Currently assumes that each element is a complete line
	public abstract void copyElement(AbstractToken token);
	
	//
	// Symbol table handler
	//
	
	public void setSymbol(String symbol, AbstractToken token)
	{
		//System.out.println("***** Adding symbol " + symbol);
		removeSymbol(symbol);	// Usually doesn't already have a definition
		_symbolTable.put(symbol, token);
	}
	
	public AbstractToken findSymbol(String symbol)
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
