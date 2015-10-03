// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Perl_EchoStatement extends TokenSequence
{
	public Perl_Keyword ECHO = new Perl_Keyword("echo");
	public Perl_Expression expr;
	public @OPT TokenList<Perl_MoreEcho> more;
	
	public static class Perl_MoreEcho extends TokenSequence
	{
		public Perl_Punctuation comma = new Perl_Punctuation(',');
		public Perl_Expression expr;
	}
}
