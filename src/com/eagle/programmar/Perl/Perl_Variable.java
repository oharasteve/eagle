// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Perl_Expression.Perl_ExpressionList;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Number;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_Variable extends TokenChooser
{
	public Perl_Identifier_Reference variable;
	
	public static class Perl_UserVariable extends TokenSequence
	{
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @OPT Perl_Punctuation pound = new Perl_Punctuation('#');
		public Perl_Identifier_Reference id;
		public @OPT TokenList<Perl_Subscript> subscript;
		public @OPT Perl_ClassField fld;
		public @OPT Perl_FunctionCall fnCall;
		public @OPT Perl_ExpressionList braces;
		
		public static class Perl_ClassField extends TokenSequence
		{
			public Perl_Punctuation arrow = new Perl_Punctuation("->");
			public Perl_Identifier_Reference fld;
			public @OPT TokenList<Perl_Subscript> subscripts;
		}
	}

	public @LAST static class Perl_DollarBraceVariable extends TokenSequence
	{
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_ExpressionList braces;
	}

	public static class Perl_DollarNumberVariable extends TokenSequence
	{
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Number number;
	}
	
	public @LAST static class Perl_DollarUnderscoreVariable extends TokenSequence
	{
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Punctuation underscore = new Perl_Punctuation('_');
	}
	
	public static class Perl_DollarBarVariable extends TokenSequence
	{
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Punctuation bar = new Perl_Punctuation('|');
	}
	
	public static class Perl_DollarSignalVariable extends TokenSequence
	{
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Keyword SIG = new Perl_Keyword("SIG");
		public PunctuationLeftBrace leftBrace;
		public Perl_Expression signal;
		public PunctuationRightBrace rightBrace;
	}
	
	public static class Perl_SpecialVariable extends TokenSequence
	{
		public Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Punctuation caret = new Perl_Punctuation('^');
		public Perl_KeywordChoice special = new Perl_KeywordChoice("O");
	}
	
	public static class Perl_PercentUTFVariable extends TokenSequence
	{
		public Perl_Punctuation percent = new Perl_Punctuation('%');
		public Perl_Keyword UTF8 = new Perl_Keyword("utf8");
		public Perl_Punctuation colonColon = new Perl_Punctuation("::");
		public Perl_Identifier_Reference id;
	}

	public static class Perl_PercentVariable extends TokenSequence
	{
		public Perl_Punctuation percent = new Perl_Punctuation('%');
		public Perl_Identifier_Reference id;
	}

	public static class Perl_AmpersandVariable extends TokenSequence
	{
		public Perl_Punctuation ampersand = new Perl_Punctuation('&');
		public Perl_Identifier_Reference id;
	}

	public static class Perl_AtVariable extends TokenSequence
	{
		public Perl_Punctuation at = new Perl_Punctuation('@');
		public Perl_Identifier_Reference id;
	}

	public @LAST static class Perl_AtUnderscoreVariable extends TokenSequence
	{
		public Perl_Punctuation at = new Perl_Punctuation('@');
		public Perl_Punctuation underscore = new Perl_Punctuation('_');
	}
	
	public static class Perl_NamespaceVariable extends TokenSequence
	{
		public Perl_Identifier_Reference id1;
		public @OPT TokenList<Perl_More_NamespaceVars> more;
		public Perl_Punctuation colonColon = new Perl_Punctuation("::");
		public @OPT Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Identifier_Reference id2;
		public @OPT Perl_NamespaceArrow arrow;
		
		public static class Perl_More_NamespaceVars extends TokenSequence
		{
			public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public Perl_Identifier_Reference id;
		}
		
		public static class Perl_NamespaceArrow extends TokenSequence
		{
			public Perl_Punctuation arrow = new Perl_Punctuation("->");
			public Perl_Keyword NEW = new Perl_Keyword("new");
			public @OPT TokenList<Perl_Subscript> subscripts;
		}
	}

	public static class Perl_ListVariable extends TokenSequence
	{
		public Perl_Keyword LIST = new Perl_Keyword("list");
		public PunctuationLeftParen leftParen;
		public SeparatedList<Perl_Expression,PunctuationComma> args;
		public PunctuationRightParen rightParen;
	}
	
	public static class Perl_FunctionCall extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT SeparatedList<Perl_Expression,PunctuationComma> parameters;
		public PunctuationRightParen rightParen;
	}
}
