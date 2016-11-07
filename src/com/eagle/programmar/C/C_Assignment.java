// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.C;

import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class C_Assignment extends TokenSequence
{
	public C_Expression expr;
	public @OPT TokenList<C_MoreAssignments> more;
	
	public static class C_MoreAssignments extends TokenSequence
	{
		public PunctuationComma comma;
		public C_Expression expr;
	}
}
