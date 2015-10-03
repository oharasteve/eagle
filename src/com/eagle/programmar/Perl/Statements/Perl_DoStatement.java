// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 26, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;

public class Perl_DoStatement extends TokenSequence
{
	public @DOC("control-structures.do.php") Perl_Keyword DO = new Perl_Keyword("do");
	public Perl_Statement stmt;
	public Perl_Keyword WHILE = new Perl_Keyword("while");
	public Perl_Expression condition;
}
