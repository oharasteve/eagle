// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 16, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.tokens.TokenSequence;

public class Perl_DieStatement extends TokenSequence
{
	public Perl_Keyword DIE = new Perl_Keyword("die");
	public Perl_Literal message;
}
