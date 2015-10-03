// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class Perl_RequireStatement extends TokenSequence
{
	public Perl_KeywordChoice REQUIRE = new Perl_KeywordChoice("require", "require_once");
	public Perl_Expression require;
}
