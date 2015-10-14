// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Symbols.Gupta_Data_Definition;
import com.eagle.programmar.Gupta.Terminals.Gupta_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Gupta_Variable_Declaration extends TokenSequence
{
	public Gupta_Type type;
	public PunctuationColon colon;
	public Gupta_Data_Definition varName;
	public @OPT Gupta_InitialValue initValue;
	
	public static class Gupta_InitialValue extends TokenSequence
	{
		public PunctuationEquals equals;
		public Gupta_Literal literal;
	}
}
