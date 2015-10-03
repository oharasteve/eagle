// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tokens.TokenList;

public class TCL_Program extends EagleLanguage
{
	public static final String NAME = "TCL";
	
	public TCL_Program()
	{
		super(NAME, new TCL_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}
	
	public TokenList<TCL_Statement> statements;
}	
