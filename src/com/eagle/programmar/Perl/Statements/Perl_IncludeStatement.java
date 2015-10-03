// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.tokens.TokenSequence;

public class Perl_IncludeStatement extends TokenSequence
{
	public @DOC("function.include.php") Perl_Keyword INCLUDE = new Perl_Keyword("include");
	public Perl_Literal filename;
}
