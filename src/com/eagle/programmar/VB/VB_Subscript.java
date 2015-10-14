// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB;

import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class VB_Subscript extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public SeparatedList<VB_Expression,PunctuationComma> exprs;
	public PunctuationRightParen rightParen;
}
