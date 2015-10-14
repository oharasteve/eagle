// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_FunctionCall extends TokenSequence
{
	public Perl_Identifier_Reference fnName;
	public @OPT TokenList<Perl_MoreFunctionName> more;
	public @OPT TokenList<Perl_Method> perlMethods;
	public PunctuationLeftParen leftParen;
	public @OPT Perl_Punctuation at = new Perl_Punctuation('@');
	public @OPT Perl_Expression parameter;
	public @OPT TokenList<Perl_MoreParameters> moreExpr;
	public PunctuationRightParen rightParen;
	
	public static class Perl_MoreFunctionName extends TokenSequence
	{
		public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public Perl_Identifier_Reference fnName;
	}
	public static class Perl_Method extends TokenSequence
	{
		public Perl_Punctuation colonColon = new Perl_Punctuation("::");
		public Perl_Identifier_Reference fnName;
	}
	
	public static class Perl_MoreParameters extends TokenSequence
	{
		public PunctuationComma comma;
		public @OPT Perl_Comment comment;
		public @OPT Perl_Punctuation at = new Perl_Punctuation('@');
		public Perl_Expression parameter;
	}
}
