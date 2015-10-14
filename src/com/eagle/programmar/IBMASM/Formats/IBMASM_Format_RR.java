// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Formats;

import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Register;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IBMASM_Format_RR extends TokenSequence
{
	public IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice(
		"ALR",		// Add logical register
		"AR",		// Add register
		"ADR",		// FP addition
		"AER",		// FP addition
		"CLR",		// Compare logical register
		"CR",		// Compare register
		"CDR",		// FP compare
		"CER",		// FP compare
		"DR",		// Divide register
		"DDR",		// FP divide
		"DER",		// FP divide
		"HDR",		// FP halve
		"HER",		// FP halve
		"LCR",		// Load complement register
		"LCDR",		// Load complement
		"LCER",		// Load complement
		"LNR",		// Load negative register
		"LNDR",		// Load negative
		"LNER",		// Load negative
		"LPR",		// Load positive register
		"LPDR",		// Load positive
		"LPER",		// Load positive
		"LR",		// Load register
		"LDR",		// Load FP register
		"LER",		// Load FP register
		"LTR",		// Load and test register
		"LTDR",		// Load and test FP register
		"LTER",		// Load and test FP register
		"MR",		// Multiply register
		"MDR",		// FP multiply
		"MER",		// FP multiply
		"NR",		// And register
		"OR",		// Or register
		"SLR",		// Subtract logical register
		"SR",		// Subtract register
		"SDR",		// FP subtraction
		"SER",		// FP subtraction
		"XR",		// Exclusive or register
		
		"BALR",		// Branch and link
		"BCTR",		// Branch on count
		"BCR",		// Branch/condition
		"ISK",		// Insert key
		"SPM",		// Set program mask
		"SSK",		// Set storage key
		
		"BASR",
		"CLCL",
		"MVCL"
		);
	
	public IBMASM_Spaces spaces;
	public IBMASM_Register register1;
	public PunctuationComma comma;
	public IBMASM_Register register2;
}
