// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenSequence;

public class C_ReturnStatement extends TokenSequence
{
	public @DOC("#The-return-Statement") C_Keyword RETURN = new C_Keyword("return");
	public @OPT C_Expression expression;
	public C_Punctuation semicolon = new C_Punctuation(';');
}
