// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class VB_Subscript extends TokenSequence
{
	public VB_Punctuation leftParen = new VB_Punctuation('(');
	public VB_Expression expr;
	public @OPT TokenList<VB_MoreSubscripts> moreSubscripts;
	public VB_Punctuation rightParen = new VB_Punctuation(')');
	
	public static class VB_MoreSubscripts extends TokenSequence
	{
		public VB_Punctuation comma = new VB_Punctuation(',');
		public VB_Expression expr;
	}
}
