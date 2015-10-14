// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_WhileStatement extends TokenSequence
{
	public @DOC("#The-while-Statement") C_Keyword WHILE = new C_Keyword("while");
	public PunctuationLeftParen leftParen;
	public C_Expression condition;
	public PunctuationRightParen rightParen;
	public @OPT C_Comment comment;
	public C_Statement whileStatement;
}
