// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Formats;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Register;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IBMASM_Format_RRS extends TokenSequence
{
	public IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice(
		"BXH",		// Branch/high
		"BXLE",		// Branch/low-equal
		"HIO",		// Halt I/O
		"LPSW",		// Load PSW
		"LM",		// Load multiple
		"RDD",		// Read direct
		"SIO",		// Start I/O
		"SSM",		// Set system mask
		"STM",		// Store multiple
		"TCH",		// Test channel
		"TIO",		// Test I/O
		"TS",		// Test-and-set
		"WRD",		// Write direct
		
		"CDS",
		"CLM",
		"CS"		// Compare and Swap
	);
	
	public IBMASM_Spaces spaces;
	public IBMASM_Register register1;
	public PunctuationComma comma1;
	public IBMASM_Register register2;
	public PunctuationComma comma2;
	public IBMASM_Address address;
}
