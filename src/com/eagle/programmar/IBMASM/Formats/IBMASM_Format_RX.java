// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Formats;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Punctuation;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Register;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Format_RX extends TokenSequence
{
	public IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice(
		"A",		// Add
		"AH",		// Add
		"AD",		// FP add
		"AE",		// FP add
		"AL",		// Add logical
		"C",		// Compare
		"CH",		// Compare
		"CD",		// FP compare
		"CE",		// FP compare
		"CL",		// Compare logical
		"D",		// Divide
		"DD",		// FP divide
		"DE",		// FP divide
		"L",		// Load
		"LH",		// Load
		"LD",		// Load FP register
		"LE",		// Load FP register
		"M",		// Multiply
		"MH",		// Multiply
		"MD",		// FP multiply
		"ME",		// FP multiply
		"N",		// And
		"O",		// Or
		"S",		// Subtract
		"SH",		// Subtract
		"SD",		// FP subtract
		"SE",		// FP subtract
		"SL",		// Subtract logical
		"ST",		// Store
		"STH",		// Store
		"STD",		// Store FP register
		"STE",		// Store FP register
		"X",		// Exclusive or
		
		"BAL",		// Branch and link
		"BC",		// Branch condition
		"BCT",		// Branch on count
		"CVB",		// Convert-binary
		"CVD",		// Convert-decimal
		"EX",		// Execute
		"IC",		// Insert character
		"LA",		// Load address
		"STC",		// Store character
		
		"BAS"
	);

	public IBMASM_Spaces spaces;
	public IBMASM_Register register;
	public IBMASM_Punctuation comma = new IBMASM_Punctuation(',');
	public IBMASM_Address address;
}
