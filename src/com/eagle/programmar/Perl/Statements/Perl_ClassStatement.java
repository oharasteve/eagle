// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Perl.Perl_Statement.Perl_SimpleStatement.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Symbols.Perl_Class_Definition;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_ClassStatement extends TokenSequence
{
	public @OPT TokenList<Perl_ClassPrefix> prefix;
	public Perl_Keyword CLASS = new Perl_Keyword("class");
	public Perl_Class_Definition cls;
	public @OPT Perl_ClassExtends extend;
	public PunctuationLeftBrace leftBrace;
	public @OPT TokenList<Perl_StatementOrComment> stmts;
	public PunctuationRightBrace rightBrace;
	public @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;

	public static class Perl_ClassPrefix extends TokenSequence
	{
		public Perl_KeywordChoice modifier = new Perl_KeywordChoice(Perl_Program.MODIFIERS);
	}
	
	public static class Perl_ClassExtends extends TokenSequence
	{
		public Perl_KeywordChoice EXTENDS = new Perl_KeywordChoice("extends", "implements");
		public TokenList<Perl_ExtendsName> extendsName;
		
		public static class Perl_ExtendsName extends TokenSequence
		{
			public @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public Perl_Identifier_Reference id;
		}
	}
}
