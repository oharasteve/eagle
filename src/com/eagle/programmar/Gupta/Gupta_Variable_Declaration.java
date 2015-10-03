// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Symbols.Gupta_Data_Definition;
import com.eagle.programmar.Gupta.Terminals.Gupta_Literal;
import com.eagle.programmar.Gupta.Terminals.Gupta_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Gupta_Variable_Declaration extends TokenSequence
{
	public Gupta_Type type;
	public Gupta_Punctuation colon = new Gupta_Punctuation(':');
	public Gupta_Data_Definition varName;
	public @OPT Gupta_InitialValue initValue;
	
	public static class Gupta_InitialValue extends TokenSequence
	{
		public Gupta_Punctuation equals = new Gupta_Punctuation('=');
		public Gupta_Literal literal;
	}
}
