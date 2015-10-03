// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Variable;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.TokenSequence;

public class VB_SetStatement extends TokenSequence
{
	public VB_Keyword SET = new VB_Keyword("set");
	public VB_Variable var;
	public VB_Punctuation equals = new VB_Punctuation('=');
	public VB_Expression expr;
}
