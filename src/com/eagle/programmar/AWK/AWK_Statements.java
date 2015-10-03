// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Statements.AWK_IfStatement;
import com.eagle.programmar.AWK.Statements.AWK_NextStatement;
import com.eagle.programmar.AWK.Statements.AWK_PrintStatement;
import com.eagle.programmar.AWK.Statements.AWK_SplitStatement;
import com.eagle.programmar.AWK.Statements.AWK_SubStatement;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class AWK_Statements extends TokenSequence
{
	public AWK_Statement statement;
	public @OPT TokenList<AWK_MoreStatements> more;
	public @OPT AWK_Comment comment;
	public @OPT TokenList<AWK_EndOfLine> newlines;
	
	public static class AWK_MoreStatements extends TokenSequence
	{
		public AWK_Punctuation semicolon = new AWK_Punctuation(';');
		@OPT public AWK_Statement statement;
	}
	
	public static class AWK_Statement extends TokenChooser
	{
		public AWK_IfStatement ifStatement;
		public AWK_NextStatement nextStatement;
		public AWK_PrintStatement printStatement;
		public AWK_SplitStatement splitStatement;
		public AWK_SubStatement subStatement;
		
		public @LAST AWK_Expression assignment;
	}
}
