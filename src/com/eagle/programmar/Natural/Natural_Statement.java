// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Statements.Natural_AcceptStatement;
import com.eagle.programmar.Natural.Statements.Natural_AtStatement;
import com.eagle.programmar.Natural.Statements.Natural_CompressStatement;
import com.eagle.programmar.Natural.Statements.Natural_ComputeStatement;
import com.eagle.programmar.Natural.Statements.Natural_DefineStatement;
import com.eagle.programmar.Natural.Statements.Natural_DisplayStatement;
import com.eagle.programmar.Natural.Statements.Natural_DivideStatement;
import com.eagle.programmar.Natural.Statements.Natural_DoStatement;
import com.eagle.programmar.Natural.Statements.Natural_EndStatement;
import com.eagle.programmar.Natural.Statements.Natural_EnterStatement;
import com.eagle.programmar.Natural.Statements.Natural_EscapeStatement;
import com.eagle.programmar.Natural.Statements.Natural_FindStatement;
import com.eagle.programmar.Natural.Statements.Natural_FormatStatement;
import com.eagle.programmar.Natural.Statements.Natural_GetStatement;
import com.eagle.programmar.Natural.Statements.Natural_HistogramStatement;
import com.eagle.programmar.Natural.Statements.Natural_IfStatement;
import com.eagle.programmar.Natural.Statements.Natural_InputStatement;
import com.eagle.programmar.Natural.Statements.Natural_LimitStatement;
import com.eagle.programmar.Natural.Statements.Natural_MoveStatement;
import com.eagle.programmar.Natural.Statements.Natural_ReadStatement;
import com.eagle.programmar.Natural.Statements.Natural_ReinputStatement;
import com.eagle.programmar.Natural.Statements.Natural_RejectStatement;
import com.eagle.programmar.Natural.Statements.Natural_ReleaseStatement;
import com.eagle.programmar.Natural.Statements.Natural_RepeatStatement;
import com.eagle.programmar.Natural.Statements.Natural_SkipStatement;
import com.eagle.programmar.Natural.Statements.Natural_SortStatement;
import com.eagle.programmar.Natural.Statements.Natural_StopStatement;
import com.eagle.programmar.Natural.Statements.Natural_StoreStatement;
import com.eagle.programmar.Natural.Statements.Natural_SuspendStatement;
import com.eagle.programmar.Natural.Statements.Natural_UpdateStatement;
import com.eagle.programmar.Natural.Statements.Natural_WriteStatement;
import com.eagle.programmar.Natural.Terminals.Natural_Comment;
import com.eagle.tokens.TokenChooser;

public class Natural_Statement extends TokenChooser
{
	// This one is a little "special".
	public Natural_Comment comment;

	public Natural_AtStatement atStatement;
	public Natural_AcceptStatement acceptStatement;
	public Natural_CompressStatement compressStatement;
	public Natural_ComputeStatement computeStatement;
	public Natural_DefineStatement defineStatement;
	public Natural_DisplayStatement displayStatement;
	public Natural_DivideStatement divideStatement;
	public Natural_DoStatement doStatement;
	public Natural_EndStatement endStatement;
	public Natural_EnterStatement enterStatement;
	public Natural_EscapeStatement escapeStatement;
	public Natural_FindStatement findStatement;
	public Natural_FormatStatement frmatStatement;
	public Natural_GetStatement getStatement;
	public Natural_HistogramStatement histogramStatement;
	public Natural_IfStatement ifStatement;
	public Natural_LimitStatement limitStatement;
	public Natural_InputStatement inputStatement;
	public Natural_MoveStatement moveStatement;
	public Natural_ReadStatement readStatement;
	public Natural_ReinputStatement reinputStatement;
	public Natural_RejectStatement rejectStatement;
	public Natural_ReleaseStatement releaseStatement;
	public Natural_RepeatStatement repeatStatement;
	public Natural_SkipStatement skipStatement;
	public Natural_SortStatement sortStatement;
	public Natural_StopStatement stopStatement;
	public Natural_StoreStatement storeStatement;
	public Natural_SuspendStatement suspendStatement;
	public Natural_UpdateStatement updateStatement;
	public Natural_WriteStatement writeStatement;
}