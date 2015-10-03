// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenList;

public class Perl_GlobalStatement extends TokenSequence
{
	public Perl_Keyword GLOBAL = new Perl_Keyword("global");
	public Perl_Variable var;
	public @OPT TokenList<Perl_MoreGlobals> moreGlobals;
	
	public static class Perl_MoreGlobals extends TokenSequence
	{
		public Perl_Punctuation comma = new Perl_Punctuation(',');
		public Perl_Variable var;
	}
}
