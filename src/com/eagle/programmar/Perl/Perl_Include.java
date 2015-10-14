// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_Include extends TokenSequence
{
	public Perl_Keyword INCLUDE = new Perl_Keyword("include");
	public PunctuationLeftParen leftParen;
	public Perl_Literal fileName;
	public PunctuationRightParen rightParen;
	public PunctuationSemicolon semicolon;
}
