// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

package com.eagle.programmar.IBMASM.Directives;

import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.tokens.TokenSequence;

public class IBMASM_CSECT_Directive extends TokenSequence
{
	public IBMASM_Keyword CSECT = new IBMASM_Keyword("CSECT");
}
