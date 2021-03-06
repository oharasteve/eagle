// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_ExpressionStatement extends TokenSequence
{
	public @DOC("#Expression-Statements") C_Expression expr;
	public PunctuationSemicolon semicolon;
}
