// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF;

import com.eagle.programmar.EagleSyntax;

public class BNF_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "BNF";
	}
	
	public BNF_Syntax()
	{
		_isCaseSensitive = false;
		_extraCharacters = "";
		_punctuationExceptions = new String[] { "::=" };
	}
}
