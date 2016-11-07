// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class VB_ArgumentList extends TokenSequence
{
	public VB_Expression arg;
	public @OPT TokenList<VB_Comment> comment;
	public @OPT TokenList<VB_MoreArguments> moreArgs;
	public @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	
	public static class VB_MoreArguments extends TokenSequence
	{
		public PunctuationComma comma;
		public @OPT TokenList<VB_Comment> comment1;
		public VB_Expression arg;
		public @OPT TokenList<VB_Comment> comment2;
	}
}
