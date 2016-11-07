// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Natural_SystemVariable extends TokenSequence
{
	public PunctuationStar star;
	public Natural_Identifier_Reference id;
	public @OPT Natural_Subscript subscript;
}
