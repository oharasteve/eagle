// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.EagleSyntax;

public class IntelASM_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "IntelASM";
	}
	
	public IntelASM_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = "_";
		_extraCharacters = "";
		_autoAdvance = false;
	}
}
