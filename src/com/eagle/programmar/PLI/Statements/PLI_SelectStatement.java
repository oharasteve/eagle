// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Statement;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenList;

public class PLI_SelectStatement extends TokenSequence
{
	public @DOC("7.51") PLI_Keyword SELECT = new PLI_Keyword("SELECT");
	public PLI_Punctuation leftParen = new PLI_Punctuation('(');
	public PLI_Expression expr;
	public PLI_Punctuation rightParen = new PLI_Punctuation(')');
	public PLI_Punctuation semiColon1 = new PLI_Punctuation(';');
	public TokenList<PLI_SelectWhenClause> selectWhens;
	public @OPT PLI_SelectOtherwise otherwise;
	public PLI_Keyword END = new PLI_Keyword("END");
	public PLI_Punctuation semiColon2 = new PLI_Punctuation(';');
	
	public static class PLI_SelectWhenClause extends TokenSequence
	{
		public PLI_Keyword WHEN = new PLI_Keyword("WHEN");
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public PLI_Literal literal;
		public @OPT TokenList<PLI_MoreLiterals> moreLiterals;
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		public PLI_Statement statement;
		
		public static class PLI_MoreLiterals extends TokenSequence
		{
			public PLI_Punctuation comma = new PLI_Punctuation(',');
			public PLI_Literal literal;
		}
	}
	
	public static class PLI_SelectOtherwise extends TokenSequence
	{
		public PLI_Keyword OTHERWISE = new PLI_Keyword("OTHERWISE");
		public @OPT PLI_Statement statement;
		public @OPT PLI_Punctuation semiColon3 = new PLI_Punctuation(';');
	}
}
