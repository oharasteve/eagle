// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Terminals.Natural_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Natural_Subscript extends TokenSequence
{
	public Natural_Punctuation leftParen = new Natural_Punctuation('(');
	public Natural_Subscript_Contents contents;
	public Natural_Punctuation rightParen = new Natural_Punctuation(')');

	public static class Natural_Subscript_Contents extends TokenChooser
	{
		public Natural_Punctuation star = new Natural_Punctuation('*');

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
				public Natural_Punctuation colon = new Natural_Punctuation(':');
				public Natural_Expression subscript;
			}
			
			public static class Natural_Second_Subscript extends TokenSequence
			{
				public Natural_Punctuation comma = new Natural_Punctuation(',');
				public Natural_Expression subscript;
				public @OPT Natural_Subscript_Range subscriptRange;
			}
		}
	}
}
