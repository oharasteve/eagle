// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 12, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Symbols.PLI_Label_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenSequence;

public class PLI_Label extends TokenSequence
{
	public PLI_Label_Definition label;
	public PLI_Punctuation colon = new PLI_Punctuation(':');
}