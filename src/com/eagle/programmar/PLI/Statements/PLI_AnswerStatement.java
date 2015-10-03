// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 9, 2014

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_AnswerStatement extends TokenSequence
{
	public PLI_Keyword ANSWER = new PLI_Keyword("ANSWER");
	public PLI_Punctuation leftParen = new PLI_Punctuation('(');
	public PLI_Expression expr;
	public PLI_Punctuation rightParen = new PLI_Punctuation(')');
	public @OPT TokenList<PLI_AnswerClause> clauses;
	public PLI_Punctuation semiColon = new PLI_Punctuation(';');
	
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
