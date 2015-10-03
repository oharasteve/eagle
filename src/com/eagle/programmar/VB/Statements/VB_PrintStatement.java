// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.TokenSequence;

public class VB_PrintStatement extends TokenSequence
{
	public VB_Keyword PRINT = new VB_Keyword("print");
	public VB_Punctuation pound = new VB_Punctuation('#');
	public VB_Number channel;
	public VB_Punctuation comma = new VB_Punctuation(',');
	public VB_Expression expr;
}
