// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Formats;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Punctuation;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Register;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Format_RSS extends TokenSequence
{
	public IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice(
		"ICM",
		"STCM"
	);
	
	public IBMASM_Spaces spaces;
	public IBMASM_Register register;
	public IBMASM_Punctuation comma1 = new IBMASM_Punctuation(',');
	public IBMASM_Address address1;
	public IBMASM_Punctuation comma2 = new IBMASM_Punctuation(',');
	public IBMASM_Address address2;
}
