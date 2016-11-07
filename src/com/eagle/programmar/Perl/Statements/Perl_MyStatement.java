// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 16, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_MyStatement extends TokenSequence
{
	public Perl_Keyword MY = new Perl_Keyword("my");
	public Perl_MyWhat what;
	public @OPT Perl_MyEquals myEquals;
	
	public static class Perl_MyWhat extends TokenChooser
	{
		public @CHOICE static class Perl_MyOne extends TokenSequence
		{
			public Perl_Variable var;
		}
		
		public @CHOICE static class Perl_MyMany extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public SeparatedList<Perl_Variable,PunctuationComma> vars;
			public PunctuationRightParen rightParen;
		}
	}
	
	public static class Perl_MyEquals extends TokenSequence
	{
		public PunctuationEquals equals;
		public Perl_Expression expression;
	}
}
