// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 29, 2014

package com.eagle.programmar.Property;

import com.eagle.programmar.EagleSyntax;

public class Property_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Property";
	}
	
	public Property_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		_autoAdvance = false;
	}
}
