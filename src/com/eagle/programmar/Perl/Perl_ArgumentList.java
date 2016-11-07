// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_ArgumentList extends TokenSequence
{
	public Perl_Expression arg;
	public @OPT TokenList<Perl_Comment> comments1;
	public @OPT TokenList<Perl_MoreArguments> moreArgs;
	public @OPT PunctuationComma comma;
	public @OPT TokenList<Perl_Comment> comments2;
	
	public static class Perl_MoreArguments extends TokenSequence
	{
		public PunctuationComma comma;
		public @OPT TokenList<Perl_Comment> comments1;
		public Perl_Expression arg;
		public @OPT TokenList<Perl_Comment> comments2;
	}
}