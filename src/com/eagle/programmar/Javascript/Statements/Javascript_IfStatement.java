// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_IfStatement extends TokenSequence
{
	public @NEWLINE @DOC("js_if_else.asp") Javascript_Keyword IF = new Javascript_Keyword("if");
	public PunctuationLeftParen leftParen;
	public @NOSPACE SeparatedList<Javascript_Expression,PunctuationComma> conditions;
	public @OPT TokenList<Javascript_Comment> comment;
	public @NOSPACE PunctuationRightParen rightParen;
	public @OPT TokenList<Javascript_Comment> comments;
	public @NEWLINE Javascript_Statement thenStatement;
	public @OPT Javascript_IfElseClause elseClause;
	
	public static class Javascript_IfElseClause extends TokenSequence
	{
		public @NEWLINE Javascript_Keyword ELSE = new Javascript_Keyword("else");
		public @NEWLINE Javascript_Statement elseStatement;
	}
}
