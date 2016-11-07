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
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Statement extends TokenChooser
{
	public @CHOICE @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
	
	public @CHOICE CSharp_Data data;
	public @CHOICE CSharp_Class myclass;
	public @CHOICE CSharp_Enum enumeration;
	
	public @CHOICE static class CSharp_StatementBlock extends TokenSequence
	{
		public @INDENT PunctuationLeftBrace leftBrace;
		public @OPT TokenList<CSharp_StatementOrComment> statements;
		public @OUTDENT PunctuationRightBrace rightBrace;
		
		public static class CSharp_StatementOrComment extends TokenChooser
		{
			public @CHOICE CSharp_Statement statement;
			public @CHOICE CSharp_Comment comment;
		}
	}

	public @CHOICE CSharp_BreakStatement breakStatement;
	public @CHOICE CSharp_ContinueStatement continueStatement;
	public @CHOICE CSharp_CheckedStatement checkedStatement;
	public @CHOICE CSharp_DoStatement doStatement;
	public @CHOICE CSharp_ForStatement forStatement;
	public @CHOICE CSharp_ForEachStatement forEachStatement;
	public @CHOICE CSharp_GetProperty getProperty;
	public @CHOICE CSharp_IfStatement ifStatement;
	public @CHOICE CSharp_LockStatement lockStatement;
	public @CHOICE CSharp_ReturnStatement returnStatement;
	public @CHOICE CSharp_SetProperty setProperty;
	public @CHOICE CSharp_SuperStatement superStatement;
	public @CHOICE CSharp_SwitchStatement switchStatement;
	public @CHOICE CSharp_SynchronizedStatement synchronizedStatement;
	public @CHOICE CSharp_ThrowStatement throwStatement;
	public @CHOICE CSharp_TryStatement tryStatement;
	public @CHOICE CSharp_UsingStatement usingStatement;
	public @CHOICE CSharp_WhileStatement whileStatement;

	// Do this one after the others, just because it is so slow
	public @CHOICE CSharp_ExpressionStatement assignmentStatement;
	
	//public @LAST CSharp_UnparsedStatement unparsed;
}
