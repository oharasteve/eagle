// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement.Python_Statement_Not_Comment.Python_Statement_Choices;
import com.eagle.programmar.Python.Statements.Python_AssertStatement;
import com.eagle.programmar.Python.Statements.Python_Assignment;
import com.eagle.programmar.Python.Statements.Python_BreakStatement;
import com.eagle.programmar.Python.Statements.Python_ClassDeclaration;
import com.eagle.programmar.Python.Statements.Python_ContinueStatement;
import com.eagle.programmar.Python.Statements.Python_DeleteStatement;
import com.eagle.programmar.Python.Statements.Python_ExecStatement;
import com.eagle.programmar.Python.Statements.Python_ExpressionStatement;
import com.eagle.programmar.Python.Statements.Python_ForStatement;
import com.eagle.programmar.Python.Statements.Python_FromStatement;
import com.eagle.programmar.Python.Statements.Python_FunctionDefinition;
import com.eagle.programmar.Python.Statements.Python_GlobalStatement;
import com.eagle.programmar.Python.Statements.Python_IfStatement;
import com.eagle.programmar.Python.Statements.Python_ImportStatement;
import com.eagle.programmar.Python.Statements.Python_PassStatement;
import com.eagle.programmar.Python.Statements.Python_PrintStatement;
import com.eagle.programmar.Python.Statements.Python_RaiseStatement;
import com.eagle.programmar.Python.Statements.Python_ReturnStatement;
import com.eagle.programmar.Python.Statements.Python_TryStatement;
import com.eagle.programmar.Python.Statements.Python_WhileStatement;
import com.eagle.programmar.Python.Statements.Python_WithStatement;
import com.eagle.programmar.Python.Statements.Python_YieldStatement;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Python_Statement extends TokenSequence
{
	public @OPT Python_EndOfLine eoln1;
	public Python_Compound_Statement statement;
	public @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
	public @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	public @OPT Python_Comment comment;
	public @OPT TokenList<Python_EndOfLine> eoln2;
	
	public static class Python_Compound_Statement extends TokenSequence
	{
		public SeparatedList<Python_Simple_Statement,PunctuationSemicolon> statements;
	}
	
	public static class Python_Simple_Statement extends TokenChooser
	{
		public @FIRST Python_Comment comment;

		public static class Python_Statement_Not_Comment extends TokenSequence
		{
			public Python_StartOfLine soln = new Python_StartOfLine();
			public Python_Statement_Choices statement;
			
			public static class Python_Statement_Choices extends TokenChooser
			{
				public @FIRST Python_Comment comment;
				
				public Python_Assignment assignment;
				public Python_AssertStatement assertStatement;
				public Python_BreakStatement breakStatement;
				public Python_ClassDeclaration classDeclaration;
				public Python_ContinueStatement continueStatement;
				public Python_DeleteStatement delStatement;
				public Python_ExecStatement execStatement;
				public Python_ForStatement forStatement;
				public Python_FromStatement fromStatement;
				public Python_FunctionDefinition functionDefinition;
				public Python_GlobalStatement globalStatement;
				public Python_IfStatement ifStatement;
				public Python_ImportStatement importStatement;
				public Python_PassStatement passStatement;
				public Python_RaiseStatement raiseStatement;
				public Python_PrintStatement printStatement;
				public Python_ReturnStatement returnStatement;
				public Python_TryStatement tryStatement;
				public Python_WhileStatement whileStatement;
				public Python_WithStatement withStatement;
				public Python_YieldStatement yieldStatement;
				
				public @LAST Python_ExpressionStatement expression;		// Avoid conflict with 'for' statement
			}
		}
	}
	
	public static class Python_SingleOrMultiLineStatement extends TokenChooser
	{
		public static class Python_SingleLineStatement extends TokenSequence
		{
			public Python_Statement_List statement;
			public @OPT Python_Comment comment;
			public @OPT Python_EndOfLine eoln;
		}

		public static class Python_MultlineStatement extends TokenSequence
		{
			public @OPT Python_Comment comment;
			public TokenList<Python_EndOfLine> eoln;
			public TokenList<Python_Statement> statements;
		}
	}
	
	public static class Python_Statement_List extends TokenSequence
	{
		public SeparatedList<Python_Statement_Choices,PunctuationSemicolon> statements;
	}
}
