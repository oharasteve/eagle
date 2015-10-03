// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.programmar.EagleSyntax;

public class JSON_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "JSON";
	}
	
	public JSON_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
	}
}
