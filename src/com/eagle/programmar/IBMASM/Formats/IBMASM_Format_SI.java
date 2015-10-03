// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Formats;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.IBMASM_Immediate;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Punctuation;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Format_SI extends TokenSequence
{
	public IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice(
		"CLI",		// Compare logical immediate
		"MVI",		// Move immediate
		"NI",		// And immediate
		"OI",		// Or immediate
		"TM",		// Test under mask
		"XI"		// Exclusive or immediate
	);
	
	public IBMASM_Spaces spaces;
	public IBMASM_Address address;
	public IBMASM_Punctuation comma2 = new IBMASM_Punctuation(',');
	public IBMASM_Immediate immediate;
}
