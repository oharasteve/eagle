// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class C_Subscript extends TokenSequence
{
	public @NOSPACE PunctuationLeftBracket leftBracket;
	public @NOSPACE @OPT C_Expression expr;
	public @NOSPACE PunctuationRightBracket rightBracket;
}
