// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

package com.eagle.programmar.CSS.Terminals;

import com.eagle.programmar.CSS.CSS_Value.CSS_NumericValue;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CSS_Color extends TokenChooser
{
	public CSS_KeywordChoice color = new CSS_KeywordChoice(
			"black",
			"blue",
			"green",
			"red",
			"white"
	);

	public static class CSS_QualifiedColor extends TokenSequence
	{
		public @OPT CSS_KeywordChoice qualifyColor = new CSS_KeywordChoice(
				"dark",
				"light"
		);
		public CSS_KeywordChoice baseColor = new CSS_KeywordChoice(
				"gray",
				"grey"
		);
	}

	public static class CSS_RGB_Value extends TokenSequence
	{
		public CSS_Keyword RGB = new CSS_Keyword("rgb");
		public @NOSPACE CSS_Punctuation leftParen = new CSS_Punctuation('(');
		public @NOSPACE CSS_Number red;
		public @NOSPACE CSS_Punctuation comma1 = new CSS_Punctuation(',');
		public CSS_Number green;
		public @NOSPACE CSS_Punctuation comma2 = new CSS_Punctuation(',');
		public CSS_Number blue;
		public @NOSPACE CSS_Punctuation rightParen = new CSS_Punctuation(')');
	}
	
	public static class CSS_RGBA_Value extends TokenSequence
	{
		public CSS_Keyword RGBA = new CSS_Keyword("rgba");
		public @NOSPACE CSS_Punctuation leftParen = new CSS_Punctuation('(');
		public @NOSPACE CSS_Number red;
		public @NOSPACE CSS_Punctuation comma1 = new CSS_Punctuation(',');
		public CSS_Number green;
		public @NOSPACE CSS_Punctuation comma2 = new CSS_Punctuation(',');
		public CSS_Number blue;
		public @NOSPACE CSS_Punctuation comma3 = new CSS_Punctuation(',');
		public CSS_Number alpha;
		public @NOSPACE CSS_Punctuation rightParen = new CSS_Punctuation(')');
		public @OPT CSS_NumericValue percentage;
	}
	
	public static class CSS_HSL_Value extends TokenSequence
	{
		public CSS_Keyword HSL = new CSS_Keyword("hsl");
		public @NOSPACE CSS_Punctuation leftParen = new CSS_Punctuation('(');
		public @NOSPACE CSS_Number hue;
		public @NOSPACE CSS_Punctuation comma1 = new CSS_Punctuation(',');
		public CSS_Number saturation;
		public @OPT CSS_Punctuation pct1 = new CSS_Punctuation('%');
		public @NOSPACE CSS_Punctuation comma2 = new CSS_Punctuation(',');
		public CSS_Number luminosity;
		public @OPT CSS_Punctuation pct2 = new CSS_Punctuation('%');
		public @NOSPACE CSS_Punctuation rightParen = new CSS_Punctuation(')');
	}
	
	public static class CSS_Transparent extends TokenSequence
	{
		public CSS_Keyword TRANSPARENT = new CSS_Keyword("transparent");
		public CSS_NumericValue percentage;
	}
}
