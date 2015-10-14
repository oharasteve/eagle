// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_IfStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.9") CSharp_Keyword IF = new CSharp_Keyword("if");
	public PunctuationLeftParen leftParen;
	public CSharp_Expression condition;
	public PunctuationRightParen rightParen;
	public @OPT TokenList<CSharp_Comment> comments1;
	public CSharp_Statement thenStatement;
	public @OPT CSharp_IfElseClause elseClause;
	
	public static class CSharp_IfElseClause extends TokenSequence
	{
		public @OPT TokenList<CSharp_Comment> comments2;
		public @NEWLINE CSharp_Keyword ELSE = new CSharp_Keyword("else");
		public @OPT TokenList<CSharp_Comment> comments3;
		public CSharp_Statement elseStatement;
	}
}
