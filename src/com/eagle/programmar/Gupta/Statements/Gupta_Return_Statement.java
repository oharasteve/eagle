// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta.Statements;

import com.eagle.programmar.Gupta.Gupta_Expression;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.tokens.TokenSequence;

public class Gupta_Return_Statement extends TokenSequence
{
	public Gupta_Keyword Return = new Gupta_Keyword("Return");
	public Gupta_Expression expression;
}
