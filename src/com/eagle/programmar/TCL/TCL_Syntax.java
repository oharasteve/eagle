// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.EagleSyntax;

public class TCL_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "TCL";
	}
	
	public TCL_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = "+";
		_extraCharacters = "_";
		_autoAdvance = false;
		_punctuationExceptions = new String[] { "--" };
		
		addReservedWords(keywords);
	}

	private String[] keywords = new String[] {
		"and",
		"not",
		"or",
	};
}