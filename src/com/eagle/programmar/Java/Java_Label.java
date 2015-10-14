// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2014

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Symbols.Java_Label_Definition;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Java_Label extends TokenSequence
{
	public Java_Label_Definition label;
	public PunctuationColon colon;
}
