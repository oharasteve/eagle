// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 7, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Statements.COBOL_AcceptStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_AddStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_CallStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_CancelStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_CloseStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_CommitStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_ComputeStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_ContinueStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_CopyStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_DeleteStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_DivideStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_EvaluateStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_ExitStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_GenerateStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_GoBackStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_GoStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_IfStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_InitializeStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_InitiateStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_InspectStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_InvokeStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_MergeStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_MoveStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_MultiplyStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_NextStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_OpenStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_ReadStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_ReleaseStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_ReturnStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_RewriteStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_SearchStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_SetStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_SortStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_StartStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_StopStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_StringStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_SubtractStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_TerminateStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_UnlockStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_UnstringStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_UseStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_WriteStatement;
import com.eagle.tokens.TokenChooser;

public class COBOL_Statement extends TokenChooser
{
	public COBOL_AcceptStatement acceptStatement;
	public COBOL_AddStatement addStatement;
	public COBOL_CallStatement callStatement;
	public COBOL_CancelStatement cancelStatement;
	public COBOL_CloseStatement closeStatement;
	public COBOL_CommitStatement commitStatement;
	public COBOL_ComputeStatement computeStatement;
	public COBOL_ContinueStatement continueStatement;
	public COBOL_CopyStatement copyStatement;
	public COBOL_DeleteStatement deleteStatement;
	public COBOL_DisplayStatement displayStatement;
	public COBOL_DivideStatement divideStatement;
	public COBOL_ExitStatement exitStatement;
	public COBOL_EvaluateStatement evaluateStatement;
	public COBOL_GenerateStatement generateStatement;
	public COBOL_GoStatement goStatement;
	public COBOL_GoBackStatement goBackStatement;
	public COBOL_IfStatement ifStatement;
	public COBOL_InitiateStatement initiateStatement;
	public COBOL_InitializeStatement initializeStatement;
	public COBOL_InvokeStatement invokeStatement;
	public COBOL_InspectStatement inspectStatement;
	public COBOL_MergeStatement mergeStatement;
	public COBOL_MoveStatement moveStatement;
	public COBOL_MultiplyStatement multiplyStatement;
	public COBOL_NextStatement nextStatement;
	public COBOL_OpenStatement openStatement;
	public COBOL_PerformStatement performStatement;
	public COBOL_ReadStatement readStatement;
	public COBOL_ReleaseStatement releaseStatement;
	public COBOL_ReturnStatement returnStatement;
	public COBOL_RewriteStatement rewruteStatement;
	public COBOL_SearchStatement searchStatement;
	public COBOL_SetStatement setStatement;
	public COBOL_SortStatement sortStatement;
	public COBOL_StartStatement startStatement;
	public COBOL_StopStatement stopStatement;
	public COBOL_StringStatement stringStatement;
	public COBOL_SubtractStatement subtractStatement;
	public COBOL_TerminateStatement terminateStatement;
	public COBOL_UnlockStatement unlockStatement;
	public COBOL_UnstringStatement unstringStatement;
	public COBOL_UseStatement useStatement;
	public COBOL_WriteStatement writeStatement;
	
	//public @LAST COBOL_UnparsedStatement unparsedStatement;
}
