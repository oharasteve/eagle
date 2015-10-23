// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.tokens.TokenChooser;
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

	public TokenList<AWK_Element> elements;
	
	public static class AWK_Element extends TokenChooser
	{
		public AWK_Command command;
		public AWK_Comment comment;
		public AWK_EndOfLine eoln;
	}
}
