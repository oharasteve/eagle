// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Data.Java_DataInitialValue;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_Statement.Java_StatementBlock.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_TryStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.20") Java_Keyword TRY = new Java_Keyword("try");
	public @OPT Java_TryResources resources;
	public @INDENT PunctuationLeftBrace leftBrace;
	public TokenList<Java_StatementOrComment> statements;
	public @OUTDENT PunctuationRightBrace rightBrace;
	public @OPT TokenList<Java_Comment> comments;
	public @OPT TokenList<Java_CatchBlock> catchBlocks;
	public @OPT Java_FinallyBlock finallyBlock;
	
	public static class Java_CatchBlock extends TokenSequence
	{
		public @NEWLINE Java_Keyword CATCH = new Java_Keyword("catch");
		public PunctuationLeftParen leftParen;
		public @OPT Java_Keyword FINAL = new Java_Keyword("final");
		public @NOSPACE Java_Type jtype;
		public @OPT TokenList<Java_MoreExceptions> more;
		public Java_Identifier id;
		public @NOSPACE PunctuationRightParen rightParen;
		public Java_Statement catchStatement;
		
		public static class Java_MoreExceptions extends TokenSequence
		{
			public Java_Punctuation vertBar = new Java_Punctuation('|');
			public Java_Type jtype;
		}
	}
	
	public static class Java_FinallyBlock extends TokenSequence
	{
		public @NEWLINE Java_Keyword FINALLY = new Java_Keyword("finally");
		public Java_Statement finallyStatement;
	}
	
	public static class Java_TryResources extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public SeparatedList<Java_TryResource,PunctuationComma> resources;
		public PunctuationRightParen rightParen;
		
		public static class Java_TryResource extends TokenSequence
		{
			public Java_Type jtype;
			public Java_Variable_Definition id;
			public Java_DataInitialValue initialValue;
		}
	}
}
