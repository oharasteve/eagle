// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Terminals.CSS_Color;
import com.eagle.programmar.CSS.Terminals.CSS_HexNumber;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_Gradient extends TokenSequence
{
	public CSS_KeywordChoice GRADIENT = new CSS_KeywordChoice(
			"linear-gradient",
			"-moz-linear-gradient",
			"-ms-linear-gradient",
			"-o-linear-gradient",
			"-webkit-linear-gradient",
			"-webkit-gradient");
	public @NOSPACE PunctuationLeftParen leftParen;
	public @NOSPACE CSS_Gradient_Piece piece;
	public @OPT TokenList<CSS_MoreGradient> moreGradients;
	public @NOSPACE PunctuationRightParen rightParen;
	
	public static class CSS_Gradient_Piece extends TokenChooser
	{
		public @CHOICE CSS_Keyword LINEAR = new CSS_Keyword("linear");
		public @CHOICE CSS_KeywordChoice direction = new CSS_KeywordChoice("top", "bottom", "left", "right");
		public @CHOICE CSS_HexNumber number;
		public @CHOICE CSS_Color color;
		
		public @CHOICE static class CSS_Gradient_Direction extends TokenSequence
		{
			public CSS_KeywordChoice fromTo = new CSS_KeywordChoice("from", "to");
			public CSS_KeywordChoice direction = new CSS_KeywordChoice("top", "bottom", "left", "right");
		}
		
		public @CHOICE static class CSS_Gradient_Source extends TokenSequence
		{
			public CSS_KeywordChoice fromTo = new CSS_KeywordChoice("from", "to");
			public PunctuationLeftParen leftParen;
			public CSS_Value value;
			public PunctuationRightParen rightParen;
		}
		
		public @CHOICE static class CSS_NumberNumber extends TokenSequence
		{
			public CSS_Number number1;
			public @OPT CSS_Number number2;
			public @OPT CSS_Punctuation percent = new CSS_Punctuation('%');
		}
	}
	
	public static class CSS_MoreGradient extends TokenSequence
	{
		public @OPT PunctuationComma comma;
		public CSS_Gradient_Piece nextPiece;
	}
}
