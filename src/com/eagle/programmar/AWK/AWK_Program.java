// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tokens.TokenList;

public class AWK_Program extends EagleLanguage
{
	public static final String NAME = "AWK";
	
	public AWK_Program()
	{
		super(NAME, new AWK_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public TokenList<AWK_Command> statements;
}
