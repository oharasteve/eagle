// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Gupta;

import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.Gupta.Declarations.Gupta_Global_Declarations;

public class Gupta_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Gupta";
	}
	
	public Gupta_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		
		findFirstWords(Gupta_Global_Declarations.class);
	}
}
