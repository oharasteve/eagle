// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;

public class Perl_ReturnStatement extends TokenSequence
{
	public @DOC("function.return.php") Perl_Keyword RETURN = new Perl_Keyword("return");
	public @OPT Perl_Expression expr;
}
