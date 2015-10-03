// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenSequence;

public class VB_OptionStatement extends TokenSequence
{
	public VB_Keyword OPTION = new VB_Keyword("option");
	public VB_Keyword EXPLICIT = new VB_Keyword("explicit");
}
