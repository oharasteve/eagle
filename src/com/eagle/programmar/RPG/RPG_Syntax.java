// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.RPG;

import com.eagle.programmar.EagleSyntax;

public class RPG_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "RPG";
	}
	
	public RPG_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = "?";
		_extraCharacters = "";
		_autoAdvance = false;
	}
}
