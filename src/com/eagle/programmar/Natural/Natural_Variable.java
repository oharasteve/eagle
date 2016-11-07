// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 7, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Natural_Variable extends TokenChooser
{
	public @CHOICE static class Natural_BuiltinVariable extends TokenSequence
	{
		public @OPT PunctuationStar star;
		public Natural_KeywordChoice builtins = new Natural_KeywordChoice("PAGE-NUMBER");
	}
	
	public @CHOICE static class Natural_UserVariable extends TokenSequence
	{
		public Natural_Identifier_Reference id;
		public @OPT TokenList<Natural_Field> fields;
		public @OPT Natural_Subscript subscript;
		
		public static class Natural_Field extends TokenSequence
		{
			public PunctuationPeriod dot;
			public Natural_Identifier_Reference id;
		}
	}
}
