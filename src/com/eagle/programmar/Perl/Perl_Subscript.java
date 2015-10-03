// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Perl_Subscript extends TokenSequence
{
	public @NOSPACE Perl_Punctuation leftBracket = new Perl_Punctuation('[');
	public @NOSPACE Perl_Expression expr;
	public @NOSPACE Perl_Punctuation rightBracket = new Perl_Punctuation(']');
}
