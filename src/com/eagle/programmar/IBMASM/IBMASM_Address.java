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
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class IBMASM_Address extends TokenSequence
{
	public IBMASM_Label_or_Star label;
	public @OPT IBMASM_AddressOffset offset;
	public @OPT IBMASM_AddressSize size;
	
	public static class IBMASM_Label_or_Star extends TokenChooser
	{
		public @CHOICE IBMASM_Label label;
		public @CHOICE IBMASM_Number number;
		public @CHOICE PunctuationStar star;
		
		public @CHOICE static class IBMASM_Address_Equals extends TokenSequence
		{
			public PunctuationEquals equals;
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
		public PunctuationLeftParen leftParen;
		public IBMASM_Number size;
		public PunctuationRightParen rightParen;
	}
}
