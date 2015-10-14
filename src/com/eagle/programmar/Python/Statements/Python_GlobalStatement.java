// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_GlobalStatement extends TokenSequence
{
	public Python_Keyword GLOBAL = new Python_Keyword("global");
	public SeparatedList<Python_Variable_Definition,PunctuationComma> vars;
}
