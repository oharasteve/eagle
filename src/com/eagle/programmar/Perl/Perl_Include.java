// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Perl_Include extends TokenSequence
{
	public Perl_Keyword INCLUDE = new Perl_Keyword("include");
	public Perl_Punctuation leftParen = new Perl_Punctuation('(');
	public Perl_Literal fileName;
	public Perl_Punctuation rightParen = new Perl_Punctuation(')');
	public Perl_Punctuation semicolon = new Perl_Punctuation(';');
}
