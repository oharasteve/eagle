// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Javascript_Subscript extends TokenSequence
{
	public @NOSPACE PunctuationLeftBracket leftBracket;
	public @NOSPACE Javascript_Expression expr;
	public @NOSPACE PunctuationRightBracket rightBracket;
}
