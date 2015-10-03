// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class VB_CallStatement extends TokenSequence
{
	public VB_Keyword CALL = new VB_Keyword("call");
	public VB_Identifier_Reference sub;
	public @OPT VB_CallParameters callParameters;
	
	public static class VB_CallParameters extends TokenSequence
	{
		public VB_Punctuation leftParen = new VB_Punctuation('(');
		public @OPT VB_Expression param;
		public @OPT TokenList<VB_MoreCallParameters> moreParams;
		public VB_Punctuation rightParen = new VB_Punctuation(')');
		
		public static class VB_MoreCallParameters extends TokenSequence
		{
			public VB_Punctuation comma = new VB_Punctuation(',');
			public VB_Expression param;
		}
	}
}
