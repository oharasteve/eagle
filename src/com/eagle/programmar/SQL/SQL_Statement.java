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
	public SQL_AlterStatement alterStmt;
	public SQL_AtAtStatement atAtStmt;
	public SQL_BeginStatement beginStmt;
	public SQL_ColumnStatement columnStmt;
	//public SQL_CommitStatement commitStmt;	// Part of a BEGIN / END transaction
	public SQL_CreateStatement createStmt;
	public SQL_DeclareStatement declareStmt;
	public SQL_DeleteStatement deleteStmt;
	public SQL_DropStatement dropStmt;
	public SQL_ForStatement forStmt;
	public SQL_GrantStatement grantStmt;
	public SQL_LoadStatement loadStmt;
	public SQL_InsertStatement insertStmt;
	public SQL_PragmaStatement pragmaStmt;
	public SQL_SelectStatement selectStmt;
	public SQL_SlashStatement slashStmt;
	public SQL_UpdateStatement updateStmt;
	public SQL_VariableStatement variableStmt;

	public @LAST SQL_ExpressionStatement expressionStmt;
}
