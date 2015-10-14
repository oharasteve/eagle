// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 7, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class COBOL_Picture_Value extends TokenChooser
{
	public @LAST COBOL_Expression expression;
	
	public static class COBOL_Picture_Value_Keyword extends TokenSequence
	{
		public @OPT PunctuationComma comma;
		public COBOL_KeywordChoice constants = new COBOL_KeywordChoice(
				"ZERO", "ZEROS", "SPACE", "SPACES",
				"LOW-VALUE", "LOW-VALUES", "HIGH-VALUE", "HIGH-VALUES");
	}

	public static class COBOL_Picture_Value_Literal extends TokenSequence
	{
		public @OPT PunctuationComma comma;
		public COBOL_Literal literal;
		public @OPT COBOL_ThruLiteral thru;
		
		public static class COBOL_ThruLiteral extends TokenSequence
		{
			public COBOL_Keyword thru = new COBOL_Keyword("THRU");
			public COBOL_Literal literal;
		}
	}

	public static class COBOL_Picture_Value_Number extends TokenSequence
	{
		public @OPT PunctuationComma comma;
		public COBOL_Number number;
	}

	public static class COBOL_Picture_Value_HexNumber extends TokenSequence
	{
		public @OPT PunctuationComma comma;
		public @OPT COBOL_Punctuation ampersand = new COBOL_Punctuation('&');
		public COBOL_HexNumber hex;
	}
	
	public static class COBOL_PictureNext extends TokenSequence
	{
		public COBOL_Keyword NEXT = new COBOL_Keyword("NEXT");
		public @OPT COBOL_PictureNextMinus minus;
		
		public static class COBOL_PictureNextMinus extends TokenSequence
		{
			public PunctuationHyphen minus;
			public COBOL_Identifier_Reference id;
		}
	}
}
