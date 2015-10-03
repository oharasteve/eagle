// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural.Terminals;

import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_Literal extends TokenSequence
{
	public Natural_Literal_Quotes lit;
	public @OPT TokenList<Natural_Literal_Continuation> moreLits;
	
	public static class Natural_Literal_Continuation extends TokenSequence
	{
		public Natural_Punctuation hyphen = new Natural_Punctuation('-');
		public Natural_Literal_Quotes lit;
	}
}