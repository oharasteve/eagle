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
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CMacro.CMacro_Statement;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_Statement extends TokenChooser
{
	public C_Data jdata;
	public C_Label label;
	public C_Punctuation semicolon = new C_Punctuation(';');	// Empty for loop body is ok
	
	public @SYNTAX(CMacro_Syntax.class) CMacro_Statement macro;
	
	public static class C_StatementBlock extends TokenSequence
	{
		public @INDENT C_Punctuation leftBrace = new C_Punctuation('{');
		public @OPT TokenList<C_StatementOrComment> statements;
		public @OUTDENT C_Punctuation rightBrace = new C_Punctuation('}');
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
