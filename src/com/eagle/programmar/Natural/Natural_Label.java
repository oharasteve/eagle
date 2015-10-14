// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 27, 2014

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Natural_Label extends TokenSequence
{
	public Natural_Identifier_Reference label;
	public PunctuationPeriod dot;
}