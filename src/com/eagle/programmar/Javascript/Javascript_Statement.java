// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Statements.Javascript_BreakStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ContinueStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_DoStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ForStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_IfStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ReturnStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_SwitchStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ThrowStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_TryStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_WhileStatement;
import com.eagle.programmar.Javascript.Symbols.Javascript_Label_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Javascript_Statement extends TokenSequence
{
	public @OPT Javascript_Label label;
	public Javascript_RealStatement statement;
	
	public static class Javascript_Label extends TokenSequence
	{
		public Javascript_Label_Definition label;
		public Javascript_Punctuation colon = new Javascript_Punctuation(':');
	}

	public static class Javascript_RealStatement extends TokenChooser
	{
		public Javascript_Data data;
		public @CURIOUS("Extra semicolon") Javascript_Punctuation semicolon = new Javascript_Punctuation(';');
		
		public static @FIRST class Javascript_StatementBlock extends TokenSequence
		{
			public @INDENT Javascript_Punctuation leftBrace = new Javascript_Punctuation('{');
			public @OPT TokenList<Javascript_StatementOrComment> statements;
			public @OUTDENT Javascript_Punctuation rightBrace = new Javascript_Punctuation('}');
		}
		
		public static class Javascript_ExpressionStatement extends TokenSequence
		{
			public @NEWLINE Javascript_Expression expression;
			public @OPT TokenList<Javascript_MoreStatements> moreStatements;
			public @OPT @NOSPACE Javascript_Punctuation semicolon = new Javascript_Punctuation(';');
				
			public static class Javascript_MoreStatements extends TokenSequence
			{
				public @NOSPACE Javascript_Punctuation comma = new Javascript_Punctuation(',');
				public Javascript_Statement statement;
			}
		}
		
		public Javascript_BreakStatement breakStatement;
		public Javascript_ContinueStatement continueStatement;
		public Javascript_DoStatement doStatement;
		public Javascript_ForStatement forStatement;
		public Javascript_IfStatement ifStatement;
		public Javascript_ReturnStatement returnStatement;
		public Javascript_SwitchStatement switchStatement;
		public Javascript_ThrowStatement throwStatement;
		public Javascript_TryStatement tryStatement;
		public Javascript_WhileStatement whileStatement;
		
		//public @LAST Javascript_UnparsedStatement unparsedStatement;
	}
	
	public static class Javascript_StatementOrComment extends TokenChooser
	{
		public Javascript_Statement statement;
		public Javascript_Comment comment;
	}
}
