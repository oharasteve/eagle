// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class AWK_Action extends TokenSequence
{
	public AWK_Punctuation leftBrace = new AWK_Punctuation('{');
	public @OPT TokenList<AWK_EndOfLine> newlines;
	public @OPT TokenList<AWK_StatementOrComment> statements;
	public AWK_Punctuation rightBrace = new AWK_Punctuation('}');
	public TokenList<AWK_EndOfLine> blankLines;
	
	public static class AWK_StatementOrComment extends TokenChooser
	{
		public AWK_Statements statements;
		public AWK_Comment comment;
	}
}
