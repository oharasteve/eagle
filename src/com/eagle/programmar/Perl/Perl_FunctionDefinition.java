// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 26, 2014

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Perl_Statement.Perl_StatementBlock;
import com.eagle.programmar.Perl.Symbols.Perl_Function_Definition;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_FunctionDefinition extends TokenSequence
{
	public @OPT TokenList<Perl_FunctionPrefix> modifiers;
	public Perl_Keyword FUNCTION = new Perl_Keyword("function");
	public Perl_Function_Definition fnName;
	public Perl_Function_Parameters params;
	public Perl_FunctionBlock block;
	
	public static class Perl_FunctionPrefix extends TokenSequence
	{
		public Perl_KeywordChoice modifier = new Perl_KeywordChoice(Perl_Program.MODIFIERS);
	}

	public static class Perl_FunctionBlock extends TokenChooser
	{
		public PunctuationSemicolon semicolon;
		public Perl_StatementBlock block;
	}
	
	public static class Perl_Function_Parameters extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT Perl_FunctionVariableOrTypeVariable var;
		public @OPT TokenList<Perl_MoreParameters> moreParams;
		public PunctuationRightParen rightParen;

		public static class Perl_FunctionVariableOrTypeVariable extends TokenChooser
		{
			public @LAST Perl_FunctionVariable var;
			
			public static class Perl_FunctionTypeAndVariable extends TokenSequence
			{
				public Perl_Type type;
				public Perl_FunctionVariable var;
			}
		}

		public static class Perl_FunctionVariable extends TokenSequence
		{
			public @OPT Perl_Punctuation amp = new Perl_Punctuation('&');
			public @OPT Perl_Punctuation dollar = new Perl_Punctuation('$');
			public Perl_Variable_Definition param;
			public @OPT Perl_Variable_Initializer init;
			
			public static class Perl_Variable_Initializer extends TokenSequence
			{
				public PunctuationEquals equals;
				public Perl_Expression initVal;
			}
		}
		
		public static class Perl_MoreParameters extends TokenSequence
		{
			public PunctuationComma comma;
			public Perl_FunctionVariableOrTypeVariable var;
		}
	}
}