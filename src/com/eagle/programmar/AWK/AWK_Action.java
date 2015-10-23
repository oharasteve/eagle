// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class AWK_Action extends TokenSequence
{
	public PunctuationLeftBrace leftBrace;
	public @OPT TokenList<AWK_StatementOrComment> statements;
	public PunctuationRightBrace rightBrace;
	public @OPT TokenList<AWK_EndOfLine> blankLines;
	
	public static class AWK_StatementOrComment extends TokenChooser
	{
		public AWK_Statements statements;
		public AWK_Comment comment;
		public AWK_EndOfLine newline;
	}
}
