// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 9, 2014

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_AnswerStatement extends TokenSequence
{
	public PLI_Keyword ANSWER = new PLI_Keyword("ANSWER");
	public PunctuationLeftParen leftParen;
	public PLI_Expression expr;
	public PunctuationRightParen rightParen;
	public @OPT TokenList<PLI_AnswerClause> clauses;
	public PunctuationSemicolon semicolon;
	
	public static class PLI_AnswerClause extends TokenChooser
	{
		public PLI_KeywordChoice SKIP = new PLI_KeywordChoice("SKIP", "NOSCAN");

		public static class PLI_AnswerCol extends TokenSequence
		{
			public PLI_Keyword COL = new PLI_Keyword("COL");
			public PLI_Expression column;
		}
	}
}
