// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Statements.SQL_AlterStatement;
import com.eagle.programmar.SQL.Statements.SQL_AtAtStatement;
import com.eagle.programmar.SQL.Statements.SQL_BeginStatement;
import com.eagle.programmar.SQL.Statements.SQL_ColumnStatement;
import com.eagle.programmar.SQL.Statements.SQL_CreateStatement;
import com.eagle.programmar.SQL.Statements.SQL_DeclareStatement;
import com.eagle.programmar.SQL.Statements.SQL_DeleteStatement;
import com.eagle.programmar.SQL.Statements.SQL_DropStatement;
import com.eagle.programmar.SQL.Statements.SQL_ExpressionStatement;
import com.eagle.programmar.SQL.Statements.SQL_ForStatement;
import com.eagle.programmar.SQL.Statements.SQL_GrantStatement;
import com.eagle.programmar.SQL.Statements.SQL_InsertStatement;
import com.eagle.programmar.SQL.Statements.SQL_LoadStatement;
import com.eagle.programmar.SQL.Statements.SQL_PragmaStatement;
import com.eagle.programmar.SQL.Statements.SQL_SelectStatement;
import com.eagle.programmar.SQL.Statements.SQL_SlashStatement;
import com.eagle.programmar.SQL.Statements.SQL_UpdateStatement;
import com.eagle.programmar.SQL.Statements.SQL_VariableStatement;
import com.eagle.tokens.TokenChooser;

public class SQL_Statement extends TokenChooser
{
	public @CHOICE SQL_AlterStatement alterStmt;
	public @CHOICE SQL_AtAtStatement atAtStmt;
	public @CHOICE SQL_BeginStatement beginStmt;
	public @CHOICE SQL_ColumnStatement columnStmt;
	//public @CHOICE SQL_CommitStatement commitStmt;	// Part of a BEGIN / END transaction
	public @CHOICE SQL_CreateStatement createStmt;
	public @CHOICE SQL_DeclareStatement declareStmt;
	public @CHOICE SQL_DeleteStatement deleteStmt;
	public @CHOICE SQL_DropStatement dropStmt;
	public @CHOICE SQL_ForStatement forStmt;
	public @CHOICE SQL_GrantStatement grantStmt;
	public @CHOICE SQL_LoadStatement loadStmt;
	public @CHOICE SQL_InsertStatement insertStmt;
	public @CHOICE SQL_PragmaStatement pragmaStmt;
	public @CHOICE SQL_SelectStatement selectStmt;
	public @CHOICE SQL_SlashStatement slashStmt;
	public @CHOICE SQL_UpdateStatement updateStmt;
	public @CHOICE SQL_VariableStatement variableStmt;

	public @LAST SQL_ExpressionStatement expressionStmt;
}
