// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Statements;

import com.eagle.programmar.Gupta.Gupta_Expression.Gupta_Function_Call;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.tokens.TokenSequence;

public class Gupta_Call_Statement extends TokenSequence
{
	public Gupta_Keyword Call = new Gupta_Keyword("Call");
	public Gupta_Function_Call fnCall;
}
