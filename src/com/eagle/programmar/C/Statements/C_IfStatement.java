// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_IfStatement extends TokenSequence
{
	public @DOC("#The-if-Statement") C_Keyword IF = new C_Keyword("if");
	public C_Punctuation leftParen = new C_Punctuation('(');
	public C_Expression condition;
	public C_Punctuation rightParen = new C_Punctuation(')');
	public @OPT TokenList<C_Comment> comments;
	public C_Statement thenStatement;
	public @OPT C_IfElseClause elseClause;
	
	public static class C_IfElseClause extends TokenSequence
	{
		public @OPT TokenList<C_Comment> comment1;
		public C_Keyword ELSE = new C_Keyword("else");
		public @OPT TokenList<C_Comment> comment2;
		public C_Statement elseStatement;
	}
}
