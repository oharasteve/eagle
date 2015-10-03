// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.VB;

import com.eagle.programmar.EagleSyntax;

public class VB_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "VB";
	}
	
	public VB_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = "_";
		_autoAdvance = false;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] { "!=", "<=", "==", ">=", "/*" };
		
		addReservedWords(keywords);
	}
	
	private String[] keywords = new String[] {
		"begin",
		"byref",
		"byval",
		"const",
		"else",
		"end",
		"for",
		"function",
		"if",
		"next",
		"sub",
		"then",
		"version"
	};
}
