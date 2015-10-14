// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_IfStatement extends TokenSequence
{
	public @DOC("control-structures.if.php") Perl_Keyword IF = new Perl_Keyword("if");
	public PunctuationLeftParen leftParen;
	public Perl_IfWhat condition;
	public @OPT TokenList<Perl_Comment> comments1;
	public PunctuationRightParen rightParen;
	public @OPT TokenList<Perl_Comment> comments2;
	public Perl_Statement thenStatement;
	public @OPT TokenList<Perl_Comment> comments3;
	public @OPT TokenList<Perl_IfElseIfClause> elseIfClauses;
	public @OPT TokenList<Perl_Comment> comments4;
	public @OPT Perl_IfElseClause elseClause;
	
	public static class Perl_IfWhat extends TokenChooser
	{
		public @LAST Perl_Expression condition;
		
		public static class Perl_IfExists extends TokenSequence
		{
			public PunctuationHyphen minus;
			public Perl_Keyword F = new Perl_Keyword("f");
			public Perl_Expression expr;
		}
	}
	
	public static class Perl_IfElseIfClause extends TokenSequence
	{
		public Perl_KeywordChoice ELSEIF = new Perl_KeywordChoice("elseif", "elsif");
		public PunctuationLeftParen leftParen;
		public Perl_Expression condition;
		public PunctuationRightParen rightParen;
		public @OPT TokenList<Perl_Comment> comments;
		public Perl_Statement elseIfStatement;
	}
	
	public static class Perl_IfElseClause extends TokenSequence
	{
		public Perl_Keyword ELSE = new Perl_Keyword("else");
		public @OPT TokenList<Perl_Comment> comments;
		public Perl_Statement elseStatement;
	}
}
