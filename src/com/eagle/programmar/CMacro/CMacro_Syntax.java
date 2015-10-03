// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.CMacro;

import com.eagle.programmar.EagleSyntax;

public class CMacro_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "CMacro";
	}
	
	public CMacro_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = "\\";
		_extraCharacters = "_";
		_autoAdvance = false;
		_punctuationExceptions = new String[] { "!=", "<=", "==", ">=", "/*", "&&", "||" };
	}
}
