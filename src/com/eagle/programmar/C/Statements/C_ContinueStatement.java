// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_ContinueStatement extends TokenSequence
{
	public @DOC("#The-continue-Statement") C_Keyword CONTINUE = new C_Keyword("continue");
	public PunctuationSemicolon semicolon;
}
