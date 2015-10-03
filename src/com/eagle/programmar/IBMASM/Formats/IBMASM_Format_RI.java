// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Formats;

import com.eagle.programmar.IBMASM.IBMASM_Immediate;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Punctuation;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Register;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Format_RI extends TokenSequence
{
	public IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice(
		"SLA",		// Shift left A/L
		"SLL",		// Shift left A/L
		"SLDA",		// Shift left double A/L
		"SLDL",		// Shift left double A/L
		"SRA",		// Shift right A/L
		"SRL",		// Shift right A/L
		"SRDA",		// Shift right double A/L
		"SRDL"		// Shift right double A/L
	);
	
	public IBMASM_Spaces spaces;
	public IBMASM_Register register;
	public IBMASM_Punctuation comma = new IBMASM_Punctuation(',');
	public IBMASM_Immediate immediate;
}
