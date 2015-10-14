// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_GlobalStatement extends TokenSequence
{
	public Perl_Keyword GLOBAL = new Perl_Keyword("global");
	public SeparatedList<Perl_Variable,PunctuationComma> vars;
}
