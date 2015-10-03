// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Statements.PLI_AllocateStatement;
import com.eagle.programmar.PLI.Statements.PLI_AnswerStatement;
import com.eagle.programmar.PLI.Statements.PLI_AssignmentStatement;
import com.eagle.programmar.PLI.Statements.PLI_BeginStatement;
import com.eagle.programmar.PLI.Statements.PLI_CallStatement;
import com.eagle.programmar.PLI.Statements.PLI_DoStatement;
import com.eagle.programmar.PLI.Statements.PLI_FormatStatement;
import com.eagle.programmar.PLI.Statements.PLI_FreeStatement;
import com.eagle.programmar.PLI.Statements.PLI_GetStatement;
import com.eagle.programmar.PLI.Statements.PLI_GoStatement;
import com.eagle.programmar.PLI.Statements.PLI_IfStatement;
import com.eagle.programmar.PLI.Statements.PLI_IterateStatement;
import com.eagle.programmar.PLI.Statements.PLI_LeaveStatement;
import com.eagle.programmar.PLI.Statements.PLI_NoteStatement;
import com.eagle.programmar.PLI.Statements.PLI_OnStatement;
import com.eagle.programmar.PLI.Statements.PLI_OpenStatement;
import com.eagle.programmar.PLI.Statements.PLI_PercentStatement;
import com.eagle.programmar.PLI.Statements.PLI_PutStatement;
import com.eagle.programmar.PLI.Statements.PLI_ReturnStatement;
import com.eagle.programmar.PLI.Statements.PLI_RevertStatement;
import com.eagle.programmar.PLI.Statements.PLI_SelectStatement;
import com.eagle.programmar.PLI.Statements.PLI_SignalStatement;
import com.eagle.programmar.PLI.Statements.PLI_StopStatement;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenChooser;

public class PLI_Statement extends TokenChooser
{
	public PLI_Punctuation semicolom = new PLI_Punctuation(';');	// Empty statement
	
	public @LAST PLI_AssignmentStatement assignmentStatement;

	public PLI_AllocateStatement allocateStatement;
	public PLI_AnswerStatement answerStatement;
	public PLI_BeginStatement beginStatement;
	public PLI_CallStatement callStatement;
	public PLI_DoStatement doStatement;
	public PLI_FormatStatement formatStatement;
	public PLI_FreeStatement freeStatement;
	public PLI_GetStatement getStatement;
	public PLI_GoStatement goStatement;
	public PLI_IfStatement ifStatement;
	public PLI_IterateStatement iterateStatement;
	public PLI_LeaveStatement leaveStatement;
	public PLI_NoteStatement noteStatement;
	public PLI_OnStatement onStatement;
	public PLI_OpenStatement openStatement;
	public PLI_PercentStatement percentStmt;
	public PLI_PutStatement putStatement;
	public PLI_ReturnStatement returnStatement;
	public PLI_RevertStatement revertStatement;
	public PLI_SignalStatement signalStatement;
	public PLI_SelectStatement selectStatement;
	public PLI_StopStatement stopStatement;
	
	public PLI_Procedure innerProcedure;
}
