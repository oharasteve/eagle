// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python;

import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_ExpressionList extends TokenSequence
{
	public SeparatedList<Python_Expression,PunctuationComma> expressions;
	public @OPT PunctuationComma comma;
}
