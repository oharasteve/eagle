// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 8, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_ChmodStatement extends TokenSequence
{
	public Perl_Keyword CHMOD = new Perl_Keyword("chmod");
	public Perl_Expression codes;
	public PunctuationComma comma;
	public Perl_Expression file;

}
