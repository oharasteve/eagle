// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_TryStatement extends TokenSequence
{
	public @NEWLINE Perl_Keyword TRY = new Perl_Keyword("try");
	public @INDENT PunctuationLeftBrace leftBrace;
	public TokenList<Perl_Statement> statements;
	public @OUTDENT PunctuationRightBrace rightBrace;
	public @OPT TokenList<Perl_Comment> comments;
	public @OPT TokenList<Perl_CatchBlock> catchBlocks;
	public @OPT Perl_FinallyBlock finallyBlock;
	
	public static class Perl_CatchBlock extends TokenSequence
	{
		public @NEWLINE Perl_Keyword CATCH = new Perl_Keyword("catch");
		public PunctuationLeftParen leftParen;
		public @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public @NOSPACE Perl_Identifier_Reference ref;
		public @OPT Perl_Punctuation dollar = new Perl_Punctuation('$');
		public Perl_Variable_Definition id;
		public @NOSPACE PunctuationRightParen rightParen;
		public Perl_Statement catchStatement;
	}
	
	public static class Perl_FinallyBlock extends TokenSequence
	{
		public @NEWLINE Perl_Keyword FINALLY = new Perl_Keyword("finally");
		public Perl_Statement finallyStatement;
	}
}
