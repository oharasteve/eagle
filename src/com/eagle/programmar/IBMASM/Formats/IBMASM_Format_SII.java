// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Formats;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.IBMASM_Immediate;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IBMASM_Format_SII extends TokenSequence
{
	public IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice(
		"SRP"
	);
	
	public IBMASM_Spaces spaces;
	public IBMASM_Address address;
	public PunctuationComma comma1;
	public IBMASM_Immediate immediate1;
	public PunctuationComma comma2;
	public IBMASM_Immediate immediate2;
}
