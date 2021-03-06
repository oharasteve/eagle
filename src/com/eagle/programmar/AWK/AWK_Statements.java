// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Statements.AWK_ForStatement;
import com.eagle.programmar.AWK.Statements.AWK_IfStatement;
import com.eagle.programmar.AWK.Statements.AWK_NextStatement;
import com.eagle.programmar.AWK.Statements.AWK_PrintStatement;
import com.eagle.programmar.AWK.Statements.AWK_SplitStatement;
import com.eagle.programmar.AWK.Statements.AWK_SubStatement;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class AWK_Statements extends TokenSequence
{
	public SeparatedList<AWK_Statement,PunctuationSemicolon> statements;
	public @OPT PunctuationSemicolon semicolon;
	public @OPT TokenList<AWK_Comment> comments;
	public @OPT AWK_EndOfLine newline;
	
	public static class AWK_Statement extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon semicolon;	// Empty statement

		public @CHOICE AWK_ForStatement forStatement;
		public @CHOICE AWK_IfStatement ifStatement;
		public @CHOICE AWK_NextStatement nextStatement;
		public @CHOICE AWK_PrintStatement printStatement;
		public @CHOICE AWK_SplitStatement splitStatement;
		public @CHOICE AWK_SubStatement subStatement;
		
		public @LAST AWK_Expression assignment;
	}
}
