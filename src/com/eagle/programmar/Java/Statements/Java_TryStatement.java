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
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Java_TryStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.20") Java_Keyword TRY = new Java_Keyword("try");
	public @OPT Java_TryResources resources;
	public @INDENT Java_Punctuation leftBrace = new Java_Punctuation('{');
	public TokenList<Java_StatementOrComment> statements;
	public @OUTDENT Java_Punctuation rightBrace = new Java_Punctuation('}');
	public @OPT TokenList<Java_Comment> comments;
	public @OPT TokenList<Java_CatchBlock> catchBlocks;
	public @OPT Java_FinallyBlock finallyBlock;
	
	public static class Java_CatchBlock extends TokenSequence
	{
		public @NEWLINE Java_Keyword CATCH = new Java_Keyword("catch");
		public Java_Punctuation leftParen = new Java_Punctuation('(');
		public @OPT Java_Keyword FINAL = new Java_Keyword("final");
		public @NOSPACE Java_Type jtype;
		public @OPT TokenList<Java_MoreExceptions> more;
		public Java_Identifier id;
		public @NOSPACE Java_Punctuation rightParen = new Java_Punctuation(')');
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
		public Java_Punctuation leftParen = new Java_Punctuation('(');
		public Java_TryResource resource;
		public @OPT TokenList<Java_TryMoreResources> more;
		public Java_Punctuation rightParen = new Java_Punctuation(')');
		
		public static class Java_TryResource extends TokenSequence
		{
			public Java_Type jtype;
			public Java_Variable_Definition id;
			public Java_DataInitialValue initialValue;
		}
		
		public static class Java_TryMoreResources extends TokenSequence
		{
			public Java_Punctuation comma = new Java_Punctuation(',');
			public Java_TryResource resource;
		}
	}
}
