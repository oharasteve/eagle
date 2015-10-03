// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 16, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Perl_MyStatement extends TokenSequence
{
	public Perl_Keyword MY = new Perl_Keyword("my");
	public Perl_MyWhat what;
	public @OPT Perl_MyEquals myEquals;
	
	public static class Perl_MyWhat extends TokenChooser
	{
		public static class Perl_MyOne extends TokenSequence
		{
			public Perl_Variable var;
		}
		
		public static class Perl_MyMany extends TokenSequence
		{
			public Perl_Punctuation leftParen = new Perl_Punctuation('(');
			public Perl_Variable var;
			public @OPT TokenList<Perl_MoreMys> moreMys;
			public Perl_Punctuation rightParen = new Perl_Punctuation(')');
			
			public static class Perl_MoreMys extends TokenSequence
			{
				public Perl_Punctuation comma = new Perl_Punctuation(',');
				public Perl_Variable var;
			}
		}
	}
	
	public static class Perl_MyEquals extends TokenSequence
	{
		public Perl_Punctuation equals = new Perl_Punctuation('=');
		public Perl_Expression expression;
	}
}
