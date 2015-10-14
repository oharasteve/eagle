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
	public C_Data jdata;
	public C_Label label;
	public PunctuationSemicolon semicolon;	// Empty for loop body is ok
	
	public @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro;
	
	public static class C_StatementBlock extends TokenSequence
	{
		public @INDENT PunctuationLeftBrace leftBrace;
		public @OPT TokenList<C_StatementOrComment> statements;
		public @OUTDENT PunctuationRightBrace rightBrace;
	}

	public C_BreakStatement breakStatement;
	public C_ContinueStatement continueStatement;
	public C_DoStatement doStatement;
	public C_ForStatement forStatement;
	public C_IfStatement ifStatement;
	public C_ReturnStatement returnStatement;
	public C_SwitchStatement switchStatement;
	public C_WhileStatement whileStatement;

	// Do this one last, just because it is so slow
	public C_ExpressionStatement assignmentStatement;
	
	//public @LAST C_UnparsedStatement unparsedStatement;
}
