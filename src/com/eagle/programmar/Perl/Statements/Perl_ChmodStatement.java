// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 8, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Perl_ChmodStatement extends TokenSequence
{
	public Perl_Keyword CHMOD = new Perl_Keyword("chmod");
	public Perl_Expression codes;
	public Perl_Punctuation comma = new Perl_Punctuation(',');
	public Perl_Expression file;

}
