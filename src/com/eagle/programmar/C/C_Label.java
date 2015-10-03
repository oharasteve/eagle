// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 27, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.Symbols.C_Label_Definition;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenSequence;

public class C_Label extends TokenSequence
{
	public C_Label_Definition label;
	public C_Punctuation colon = new C_Punctuation(':');
}
