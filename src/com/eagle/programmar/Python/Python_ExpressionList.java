// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_ExpressionList extends TokenSequence
{
	public Python_Expression expression;
	public @OPT TokenList<Python_MoreExpressions> moreExpressions;
	public @OPT Python_Punctuation comma = new Python_Punctuation(',');
	
	public static class Python_MoreExpressions extends TokenSequence
	{
		public Python_Punctuation comma = new Python_Punctuation(',');
		public Python_Expression expression;
	}
}
