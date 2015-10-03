// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Statements;

import com.eagle.programmar.TCL.Symbols.TCL_Variable_Definition;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;

public class TCL_VariableStatement extends TokenSequence
{
	public TCL_Keyword VARIABLE = new TCL_Keyword("variable");
	public TCL_Variable_Definition variable;
}
