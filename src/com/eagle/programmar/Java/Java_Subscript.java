// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Java_Subscript extends TokenSequence
{
	public @NOSPACE Java_Punctuation leftBracket = new Java_Punctuation('[');
	public @NOSPACE Java_Expression expr;
	public @NOSPACE Java_Punctuation rightBracket = new Java_Punctuation(']');
}
