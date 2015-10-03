// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Perl_WhileStatement extends TokenSequence
{
	public @DOC("control-structures.while.php") Perl_Keyword WHILE = new Perl_Keyword("while");
	public Perl_Punctuation leftParen = new Perl_Punctuation('(');
	public Perl_Expression condition;
	public Perl_Punctuation rightParen = new Perl_Punctuation(')');
	public Perl_Statement stmt;
}
