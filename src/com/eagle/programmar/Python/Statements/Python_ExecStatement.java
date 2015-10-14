// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 7, 2014

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_ExecStatement extends TokenSequence
{
	public Python_Keyword EXEC = new Python_Keyword("exec");
	public SeparatedList<Python_Expression,PunctuationComma> exprs;
}
