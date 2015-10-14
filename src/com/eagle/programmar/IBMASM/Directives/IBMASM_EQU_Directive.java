// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 11, 2011

package com.eagle.programmar.IBMASM.Directives;

import com.eagle.programmar.IBMASM.IBMASM_Immediate;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationStar;

public class IBMASM_EQU_Directive extends TokenSequence
{
	public IBMASM_Keyword EQU = new IBMASM_Keyword("EQU");
	public IBMASM_Spaces spaces;
	public IBMASM_EQU_What what;
	
	public static class IBMASM_EQU_What extends TokenChooser
	{
		public PunctuationStar star;
		public IBMASM_Immediate immediage;
	}
}
