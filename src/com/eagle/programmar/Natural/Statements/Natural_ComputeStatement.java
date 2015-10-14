// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Natural_ComputeStatement extends TokenSequence
{
	public @DOC("sm/compute.htm") Natural_Keyword COMPUTE = new Natural_Keyword("COMPUTE");
	public Natural_Variable var;
	public PunctuationEquals equals;
	public Natural_Expression expr;
}
