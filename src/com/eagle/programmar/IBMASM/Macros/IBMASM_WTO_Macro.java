// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 13, 2011

package com.eagle.programmar.IBMASM.Macros;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class IBMASM_WTO_Macro extends TokenSequence
{
	public IBMASM_Spaces spaces1;
	public IBMASM_Keyword WTO = new IBMASM_Keyword("WTO");
	public IBMASM_Spaces spaces2;
	public IBMASM_WTO_Value value;
	public IBMASM_Spaces spaces3;
	public @OPT IBMASM_Remark remark;
	
	public static class IBMASM_WTO_Value extends TokenChooser
	{
		public @CHOICE static class IBMASM_WTO_E extends TokenSequence
		{
			public IBMASM_Keyword MF = new IBMASM_Keyword("MF");
			public PunctuationEquals equals;
			public PunctuationLeftParen leftParen;
			public IBMASM_Keyword E = new IBMASM_Keyword("E");
			public PunctuationComma comma;
			public IBMASM_Address address;
			public PunctuationRightParen rightParen;
		}
	}
}
