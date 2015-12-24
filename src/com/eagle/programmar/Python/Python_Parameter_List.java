// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 23, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Parameter_List.Python_Params.Python_MoreParams.Python_InitValue;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Python_Parameter_List extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public @OPT Python_Comment comment;
	public @SYNTAX(Python_Multiline_Syntax.class) Python_Params params;
	public @OPT Python_EndOfLine eoln;
	public PunctuationRightParen rightParen;
	
	public static class Python_Params extends TokenSequence
	{
		public @OPT PunctuationStar star;
		public @OPT Python_Punctuation starStar = new Python_Punctuation("**");
		public @OPT Python_Expression expr;
		public @OPT Python_InitValue initValue;
		public @OPT TokenList<Python_MoreParams> moreParams;
		
		public static class Python_MoreParams extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT Python_Comment comment;
			public @OPT PunctuationStar star;
			public @OPT Python_Punctuation starStar = new Python_Punctuation("**");
			public @OPT Python_Expression expr;
			public @OPT Python_InitValue initValue;
			
			public static class Python_InitValue extends TokenSequence
			{
				public PunctuationEquals equals;
				public Python_Expression defaultValue;
			}
		}
	}
}
