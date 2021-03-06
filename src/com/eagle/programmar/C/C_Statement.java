// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Statements.C_BreakStatement;
import com.eagle.programmar.C.Statements.C_ContinueStatement;
import com.eagle.programmar.C.Statements.C_DoStatement;
import com.eagle.programmar.C.Statements.C_ExpressionStatement;
import com.eagle.programmar.C.Statements.C_ForStatement;
import com.eagle.programmar.C.Statements.C_IfStatement;
import com.eagle.programmar.C.Statements.C_ReturnStatement;
import com.eagle.programmar.C.Statements.C_SwitchStatement;
import com.eagle.programmar.C.Statements.C_WhileStatement;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_Statement extends TokenChooser
{
	public @CHOICE C_Data jdata;
	public @CHOICE C_Label label;
	public @CHOICE PunctuationSemicolon semicolon;	// Empty for loop body is ok
	
	public @CHOICE @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro;
	
	public @CHOICE static class C_StatementBlock extends TokenSequence
	{
		public @INDENT PunctuationLeftBrace leftBrace;
		public @OPT TokenList<C_StatementOrComment> statements;
		public @OUTDENT PunctuationRightBrace rightBrace;
	}

	public @CHOICE C_BreakStatement breakStatement;
	public @CHOICE C_ContinueStatement continueStatement;
	public @CHOICE C_DoStatement doStatement;
	public @CHOICE C_ForStatement forStatement;
	public @CHOICE C_IfStatement ifStatement;
	public @CHOICE C_ReturnStatement returnStatement;
	public @CHOICE C_SwitchStatement switchStatement;
	public @CHOICE C_WhileStatement whileStatement;

	// Do this one last, just because it is so slow
	public @CHOICE C_ExpressionStatement assignmentStatement;
	
	//public @LAST C_UnparsedStatement unparsedStatement;
}
