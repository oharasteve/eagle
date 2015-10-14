// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta;

import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Gupta_Subscript extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public Gupta_Expression expr;
	public PunctuationRightParen rightParen;
}
