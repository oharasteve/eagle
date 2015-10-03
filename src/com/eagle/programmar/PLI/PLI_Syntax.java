// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.PLI;

import com.eagle.programmar.EagleSyntax;

public class PLI_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "PLI";
	}
	
	public PLI_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "_";
	}
}
