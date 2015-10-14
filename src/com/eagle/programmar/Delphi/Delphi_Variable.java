// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Delphi_Variable extends TokenSequence
{
	public Delphi_Identifier_Reference var;
	public @OPT TokenList<Delphi_Extended_Variable> extensions;

	public static class Delphi_Extended_Variable extends TokenChooser
	{
		public static class Delphi_DotName extends TokenSequence
		{
			public PunctuationPeriod dot;
			public Delphi_Identifier_Reference var;
		}
	
		public static class Delphi_Subscript extends TokenSequence
		{
			public PunctuationLeftBracket leftBracket;
			public Delphi_Expression expr;
			public PunctuationRightBracket rightBracket;
		}
	}
}
