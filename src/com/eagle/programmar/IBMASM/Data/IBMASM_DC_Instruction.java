// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Data;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class IBMASM_DC_Instruction extends TokenSequence
{
	public @OPT IBMASM_Label_Definition label;
	public IBMASM_Spaces spaces1;
	public IBMASM_Keyword DC = new IBMASM_Keyword("DC");
	public IBMASM_Spaces spaces2;
	public IBMASM_DC_Value value;
	public IBMASM_Spaces spaces3;
	public @OPT IBMASM_Remark remark;

	public static class IBMASM_DC_Value extends TokenChooser
	{
		public @CHOICE static class IBMASM_DC_Address extends TokenSequence
		{
			public IBMASM_Keyword A = new IBMASM_Keyword("A");
			public PunctuationLeftParen leftParen;
			public IBMASM_Address address;
			public PunctuationRightParen rightParen;
		}
		
		public @CHOICE static class IBMASM_DC_Length extends TokenSequence
		{
			public @OPT IBMASM_Number replication;
			public IBMASM_KeywordChoice code = new IBMASM_KeywordChoice("CL", "XL");
			public IBMASM_Number number;
			public IBMASM_Literal literal;
		}
		
		public @CHOICE static class IBMASM_DC_Literal extends TokenSequence
		{
			public IBMASM_KeywordChoice code = new IBMASM_KeywordChoice(
					"B", "C", "E", "F", "G", "H", "P", "X");
			public IBMASM_Literal literal;
		}
	}
}
