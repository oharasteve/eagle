// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenSequence;

public class Python_FromStatement extends TokenSequence
{
	public Python_Keyword FROM = new Python_Keyword("from");
	public Python_Variable fromName;
	public Python_ImportStatement importStatement;
}
