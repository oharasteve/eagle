// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.programmar.Javascript.Javascript_Statement.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Javascript_TryStatement extends TokenSequence
{
	public @NEWLINE @DOC("js_try_catch.asp") Javascript_Keyword TRY = new Javascript_Keyword("try");
	public @INDENT Javascript_Punctuation leftBrace = new Javascript_Punctuation('{');
	public @OPT TokenList<Javascript_StatementOrComment> statements;
	public @OUTDENT Javascript_Punctuation rightBrace = new Javascript_Punctuation('}');
	public @OPT TokenList<Javascript_CatchBlock> catchBlocks;
	public @OPT Javascript_FinallyBlock finallyBlock;
	
	public static class Javascript_CatchBlock extends TokenSequence
	{
		public @NEWLINE Javascript_Keyword CATCH = new Javascript_Keyword("catch");
		public Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
		public @NOSPACE Javascript_Variable_Definition id;
		public @NOSPACE Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
		public Javascript_Statement catchStatement;
	}
	
	public static class Javascript_FinallyBlock extends TokenSequence
	{
		public @NEWLINE Javascript_Keyword FINALLY = new Javascript_Keyword("finally");
		public Javascript_Statement finallyStatement;
	}
}
