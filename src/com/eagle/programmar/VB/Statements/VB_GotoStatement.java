// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenSequence;

public class VB_GotoStatement extends TokenSequence
{
	public VB_Keyword GOTO = new VB_Keyword("goto");
	public VB_Identifier_Reference lbl;
}
