// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural;

import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Natural_Subscript extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public Natural_Subscript_Contents contents;
	public PunctuationRightParen rightParen;

	public static class Natural_Subscript_Contents extends TokenChooser
	{
		public PunctuationStar star;

		public static class Natural_Subscript_Contents_Label extends TokenSequence
		{
			public Natural_Label label;
		}
		
		public static class Natural_Subscript_Contents_Normal extends TokenSequence
		{
			public Natural_Expression subscript;
			public @OPT Natural_Subscript_Range subscriptRange;
			public @OPT Natural_Second_Subscript secondSubscript;
			
			public static class Natural_Subscript_Range extends TokenSequence
			{
				public PunctuationColon colon;
				public Natural_Expression subscript;
			}
			
			public static class Natural_Second_Subscript extends TokenSequence
			{
				public PunctuationComma comma;
				public Natural_Expression subscript;
				public @OPT Natural_Subscript_Range subscriptRange;
			}
		}
	}
}
