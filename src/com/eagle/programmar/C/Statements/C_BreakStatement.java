// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_BreakStatement extends TokenSequence
{
	public @DOC("#The-break-Statement") C_Keyword BREAK = new C_Keyword("break");
	public PunctuationSemicolon semicolon;
}
