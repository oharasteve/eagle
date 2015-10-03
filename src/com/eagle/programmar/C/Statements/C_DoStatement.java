// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenSequence;

public class C_DoStatement extends TokenSequence
{
	public @DOC("#The-do-Statement") C_Keyword DO = new C_Keyword("do");
	public @OPT C_Comment comment;
	public C_Statement doStatement;
	public C_Keyword WHILE = new C_Keyword("while");
	public C_Punctuation leftParen = new C_Punctuation('(');
	public C_Expression condition;
	public C_Punctuation rightParen = new C_Punctuation(')');
	public C_Punctuation semicolon = new C_Punctuation(';');
}
