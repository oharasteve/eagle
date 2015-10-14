// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Perl_VarStatement extends TokenSequence
{
	public TokenList<Perl_VarPrefix> prefix;
	public @OPT Perl_Punctuation dollar = new Perl_Punctuation('$');
	public Perl_Variable_Definition var;
	public @OPT Perl_Variable_Initializer init;
	
	public static class Perl_VarPrefix extends TokenChooser
	{
		public Perl_KeywordChoice modifier = new Perl_KeywordChoice(Perl_Program.MODIFIERS);
	}

	public static class Perl_Variable_Initializer extends TokenSequence
	{
		public PunctuationEquals equals;
		public Perl_Expression initVal;
	}
}
