// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.IBMASM;

import com.eagle.programmar.EagleSyntax;

public class IBMASM_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "IBMASM";
	}
	
	public IBMASM_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = "_";
		_extraCharacters = "";
		_allowDigitsInKeywords = false;		// So XL4 comes back as XL then 4 as two separate tokens
		_autoAdvance = false;
	}
}
