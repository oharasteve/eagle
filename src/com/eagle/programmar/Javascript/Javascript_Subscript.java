// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Javascript_Subscript extends TokenSequence
{
	public @NOSPACE Javascript_Punctuation leftBracket = new Javascript_Punctuation('[');
	public @NOSPACE Javascript_Expression expr;
	public @NOSPACE Javascript_Punctuation rightBracket = new Javascript_Punctuation(']');
}
