// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 23, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Parameter_List.Python_Params.Python_MoreParams.Python_InitValue;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_Parameter_List extends TokenSequence
{
	public Python_Punctuation leftParen = new Python_Punctuation('(');
	public @OPT Python_Comment comment;
	public @SYNTAX(Python_Multiline_Syntax.class) Python_Params params;
	public Python_Punctuation rightParen = new Python_Punctuation(')');
	
	public static class Python_Params extends TokenSequence
	{
		public @OPT Python_Punctuation star = new Python_Punctuation('*');
		public @OPT Python_Punctuation starStar = new Python_Punctuation("**");
		public @OPT Python_Expression expr;
		public @OPT Python_InitValue initValue;
		public @OPT TokenList<Python_MoreParams> moreParams;
		
		public static class Python_MoreParams extends TokenSequence
		{
			public Python_Punctuation comma = new Python_Punctuation(',');
			public @OPT Python_Comment comment;
			public @OPT Python_Punctuation star = new Python_Punctuation('*');
			public @OPT Python_Punctuation starStar = new Python_Punctuation("**");
			public @OPT Python_Expression expr;
			public @OPT Python_InitValue initValue;
			
			public static class Python_InitValue extends TokenSequence
			{
				public Python_Punctuation equals = new Python_Punctuation('=');
				public Python_Expression defaultValue;
			}
		}
	}
}
