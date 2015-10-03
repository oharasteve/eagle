// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_IfStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.9") CSharp_Keyword IF = new CSharp_Keyword("if");
	public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
	public CSharp_Expression condition;
	public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
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
