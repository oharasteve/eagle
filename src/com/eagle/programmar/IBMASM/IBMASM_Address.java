// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 11, 2011

package com.eagle.programmar.IBMASM;

import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Label;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Address extends TokenSequence
{
	public IBMASM_Label_or_Star label;
	public @OPT IBMASM_AddressOffset offset;
	public @OPT IBMASM_AddressSize size;
	
	public static class IBMASM_Label_or_Star extends TokenChooser
	{
		public IBMASM_Label label;
		public IBMASM_Number number;
		public IBMASM_Punctuation star = new IBMASM_Punctuation('*');
		
		public static class IBMASM_Address_Equals extends TokenSequence
		{
			public IBMASM_Punctuation equals = new IBMASM_Punctuation('=');
			public IBMASM_Keyword X = new IBMASM_Keyword("X");
			public IBMASM_Literal literal;
		}
	}
	
	public static class IBMASM_AddressOffset extends TokenSequence
	{
		public IBMASM_Punctuation plus = new IBMASM_Punctuation('+');
		public IBMASM_Number offset;
	}
	
	public static class IBMASM_AddressSize extends TokenSequence
	{
		public IBMASM_Punctuation leftParen = new IBMASM_Punctuation('(');
		public IBMASM_Number size;
		public IBMASM_Punctuation rightParen = new IBMASM_Punctuation(')');
	}
}
