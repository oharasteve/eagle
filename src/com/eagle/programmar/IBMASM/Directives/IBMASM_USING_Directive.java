// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

package com.eagle.programmar.IBMASM.Directives;

import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Punctuation;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Register;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;

public class IBMASM_USING_Directive extends TokenSequence
{
	public IBMASM_Keyword USING = new IBMASM_Keyword("USING");
	public IBMASM_Spaces spaces;
	public IBMASM_Punctuation star = new IBMASM_Punctuation('*');
	public IBMASM_Punctuation comma = new IBMASM_Punctuation(',');
	public IBMASM_Register register;
}
