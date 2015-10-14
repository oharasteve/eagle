// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Statement;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_SelectStatement extends TokenSequence
{
	public @DOC("7.51") PLI_Keyword SELECT = new PLI_Keyword("SELECT");
	public PunctuationLeftParen leftParen;
	public PLI_Expression expr;
	public PunctuationRightParen rightParen;
	public PunctuationSemicolon semicolon1;
	public TokenList<PLI_SelectWhenClause> selectWhens;
	public @OPT PLI_SelectOtherwise otherwise;
	public PLI_Keyword END = new PLI_Keyword("END");
	public PunctuationSemicolon semicolon2;
	
	public static class PLI_SelectWhenClause extends TokenSequence
	{
		public PLI_Keyword WHEN = new PLI_Keyword("WHEN");
		public PunctuationLeftParen leftParen;
		public SeparatedList<PLI_Literal,PunctuationComma> literals;
		public PunctuationRightParen rightParen;
		public PLI_Statement statement;
	}
	
	public static class PLI_SelectOtherwise extends TokenSequence
	{
		public PLI_Keyword OTHERWISE = new PLI_Keyword("OTHERWISE");
		public @OPT PLI_Statement statement;
		public @OPT PLI_Punctuation semiColon3 = new PLI_Punctuation(';');
	}
}
