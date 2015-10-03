// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Symbols.Perl_Package_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;

public class Perl_PackageStatement extends TokenSequence
{
	public Perl_Keyword PACKAGE = new Perl_Keyword("package");
	public Perl_Package_Definition pkg;
}
