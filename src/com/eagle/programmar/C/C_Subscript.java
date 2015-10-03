// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenSequence;

public class C_Subscript extends TokenSequence
{
	public @NOSPACE C_Punctuation leftBracket = new C_Punctuation('[');
	public @NOSPACE @OPT C_Expression expr;
	public @NOSPACE C_Punctuation rightBracket = new C_Punctuation(']');
}
