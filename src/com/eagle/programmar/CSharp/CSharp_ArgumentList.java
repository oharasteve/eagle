// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CSharp_ArgumentList extends TokenSequence
{
	public @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice("ref", "out");
	public CSharp_Expression arg;
	public @OPT TokenList<CSharp_MoreArguments> moreArgs;
	
	public static class CSharp_MoreArguments extends TokenSequence
	{
		public PunctuationComma comma;
		public @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice("ref", "out");
		public CSharp_Expression arg;
		public @OPT TokenList<CSharp_Comment> comments;
	}
}