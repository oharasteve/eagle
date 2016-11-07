// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.C;

import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_ParenthesizedExpression extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public C_Expression expression;
	public PunctuationRightParen rightParen;
}
