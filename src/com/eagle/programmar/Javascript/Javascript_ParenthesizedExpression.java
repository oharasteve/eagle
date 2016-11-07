// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.Javascript;

import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_ParenthesizedExpression extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public @OPT @NOSPACE SeparatedList<Javascript_Expression,PunctuationComma> expressions;
	public @OPT PunctuationComma comma;
	public @NOSPACE PunctuationRightParen rightParen;
}
