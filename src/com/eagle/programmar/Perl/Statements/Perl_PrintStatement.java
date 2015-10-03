// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.tokens.TokenSequence;

public class Perl_PrintStatement extends TokenSequence
{
	public Perl_KeywordChoice PRINT = new Perl_KeywordChoice("print", "printf");
	public Perl_Expression value;
	public @OPT Perl_Literal literal;		// Second literal
}
