// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 27, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.Symbols.C_Label_Definition;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class C_Label extends TokenSequence
{
	public C_Label_Definition label;
	public PunctuationColon colon;
}
