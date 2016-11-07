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
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_Statement extends TokenChooser
{
	public @CHOICE PunctuationSemicolon semicolon;	// Empty statement
	
	public @LAST PLI_AssignmentStatement assignmentStatement;

	public @CHOICE PLI_AllocateStatement allocateStatement;
	public @CHOICE PLI_AnswerStatement answerStatement;
	public @CHOICE PLI_BeginStatement beginStatement;
	public @CHOICE PLI_CallStatement callStatement;
	public @CHOICE PLI_DoStatement doStatement;
	public @CHOICE PLI_FormatStatement formatStatement;
	public @CHOICE PLI_FreeStatement freeStatement;
	public @CHOICE PLI_GetStatement getStatement;
	public @CHOICE PLI_GoStatement goStatement;
	public @CHOICE PLI_IfStatement ifStatement;
	public @CHOICE PLI_IterateStatement iterateStatement;
	public @CHOICE PLI_LeaveStatement leaveStatement;
	public @CHOICE PLI_NoteStatement noteStatement;
	public @CHOICE PLI_OnStatement onStatement;
	public @CHOICE PLI_OpenStatement openStatement;
	public @CHOICE PLI_PercentStatement percentStmt;
	public @CHOICE PLI_PutStatement putStatement;
	public @CHOICE PLI_ReturnStatement returnStatement;
	public @CHOICE PLI_RevertStatement revertStatement;
	public @CHOICE PLI_SignalStatement signalStatement;
	public @CHOICE PLI_SelectStatement selectStatement;
	public @CHOICE PLI_StopStatement stopStatement;
	
	public @CHOICE PLI_Procedure innerProcedure;
}
