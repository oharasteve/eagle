// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;

public class Perl_BreakStatement extends TokenSequence
{
	public @DOC("control-structures.break.php") Perl_Keyword BREAK = new Perl_Keyword("break");
	public @OPT Perl_Expression count;
}
