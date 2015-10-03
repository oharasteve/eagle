// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Statements.CSharp_BreakStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_CheckedStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ContinueStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_DoStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ExpressionStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ForEachStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ForStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_GetProperty;
import com.eagle.programmar.CSharp.Statements.CSharp_IfStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_LockStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ReturnStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_SetProperty;
import com.eagle.programmar.CSharp.Statements.CSharp_SwitchStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_SynchronizedStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ThrowStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_TryStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_UsingStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_WhileStatement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Statement extends TokenChooser
{
	public @CURIOUS("Extra semicolon") CSharp_Punctuation semicolon = new CSharp_Punctuation(';');
	
	public CSharp_Data data;
	public CSharp_Class myclass;
	public CSharp_Enum enumeration;
	
	public static class CSharp_StatementBlock extends TokenSequence
	{
		public @INDENT CSharp_Punctuation leftBrace = new CSharp_Punctuation('{');
		public @OPT TokenList<CSharp_StatementOrComment> statements;
		public @OUTDENT CSharp_Punctuation rightBrace = new CSharp_Punctuation('}');
		
		public static class CSharp_StatementOrComment extends TokenChooser
		{
			public CSharp_Statement statement;
			public CSharp_Comment comment;
		}
	}

	public CSharp_BreakStatement breakStatement;
	public CSharp_ContinueStatement continueStatement;
	public CSharp_CheckedStatement checkedStatement;
	public CSharp_DoStatement doStatement;
	public CSharp_ForStatement forStatement;
	public CSharp_ForEachStatement forEachStatement;
	public CSharp_GetProperty getProperty;
	public CSharp_IfStatement ifStatement;
	public CSharp_LockStatement lockStatement;
	public CSharp_ReturnStatement returnStatement;
	public CSharp_SetProperty setProperty;
	public CSharp_SuperStatement superStatement;
	public CSharp_SwitchStatement switchStatement;
	public CSharp_SynchronizedStatement synchronizedStatement;
	public CSharp_ThrowStatement throwStatement;
	public CSharp_TryStatement tryStatement;
	public CSharp_UsingStatement usingStatement;
	public CSharp_WhileStatement whileStatement;

	// Do this one after the others, just because it is so slow
	public CSharp_ExpressionStatement assignmentStatement;
	
	//public @LAST CSharp_UnparsedStatement unparsed;
}
