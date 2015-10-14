// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_Statement.CSharp_StatementBlock.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_TryStatement extends TokenSequence
{
	public @DOC("statements.html#14.20") CSharp_Keyword TRY = new CSharp_Keyword("try");
	public PunctuationLeftBrace leftBrace;
	public TokenList<CSharp_StatementOrComment> statements;
	public PunctuationRightBrace rightBrace;
	public @OPT TokenList<CSharp_Comment> comments;
	public @OPT TokenList<CSharp_CatchBlock> catchBlocks;
	public @OPT CSharp_FinallyBlock finallyBlock;
	
	public static class CSharp_CatchBlock extends TokenSequence
	{
		public CSharp_Keyword CATCH = new CSharp_Keyword("catch");
		public @OPT CSharp_CatchWhat catchWhat;
		public CSharp_Statement catchStatement;
		
		public static class CSharp_CatchWhat extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public CSharp_Type cstype;
			public @OPT CSharp_Identifier id;
			public PunctuationRightParen rightParen;
		}
	}
	
	public static class CSharp_FinallyBlock extends TokenSequence
	{
		public CSharp_Keyword FINALLY = new CSharp_Keyword("finally");
		public CSharp_Statement finallyStatement;
	}
}
