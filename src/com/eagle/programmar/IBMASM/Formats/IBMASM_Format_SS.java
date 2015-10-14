// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Formats;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IBMASM_Format_SS extends TokenSequence
{
	public IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice(
		"AP",		// Add packed
		"CLC",		// Compare logical chars
		"CP",		// Compare packed
		"DP",		// Divide packed
		"ED",		// Edit
		"EDMK",		// Edit and mark
		"MP",		// Multiply packed
		"MVC",		// Move character
		"MVN",		// Move numeric
		"MVO",		// Move with offset
		"MVZ",		// Move zone
		"NC",		// And characters
		"OC",		// Or characters
		"PACK",		// Pack (Character to decimal)
		"SP",		// Subtract packed
		"TR",		// Translate
		"TRT",		// Translate and test
		"UNPK",		// Unpack
		"XC",		// Exclusive or characters
		"ZAP",		// Zero and add packed
		
		"MVCIN"
	);
	
	public IBMASM_Spaces spaces;
	public IBMASM_Address address1;
	public PunctuationComma comma;
	public IBMASM_Address address2;
}
