// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class AWK_ArgumentList extends TokenSequence
{
	public AWK_Expression expr;
	public @OPT TokenList<AWK_MoreArguments> more;
	
	public static class AWK_MoreArguments extends TokenSequence
	{
		public AWK_Punctuation comma = new AWK_Punctuation(',');
		public AWK_Expression expr;
	}
}
