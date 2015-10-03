// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 13, 2011

package com.eagle.programmar.IBMASM;

import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Immediate extends TokenChooser
{
	public IBMASM_Number number;
	public IBMASM_Address address;
	
	public static class IBMASM_Immediate_Data extends TokenSequence
	{
		public IBMASM_KeywordChoice code = new IBMASM_KeywordChoice("C", "X");
		public IBMASM_Literal literal;
	}
}
