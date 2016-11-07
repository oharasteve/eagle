// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 26, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Perl_ThrowStatement extends TokenSequence
{
	public Perl_Keyword THROW = new Perl_Keyword("throw");
	public @OPT Perl_Keyword NEW = new Perl_Keyword("new");
	public Perl_Expression expr;

	public static class Perl_MoreException extends TokenSequence
	{
		public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public Perl_Identifier_Reference id;
	}
}
