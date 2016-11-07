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
	public @CHOICE Natural_Comment comment;

	public @CHOICE Natural_AtStatement atStatement;
	public @CHOICE Natural_AcceptStatement acceptStatement;
	public @CHOICE Natural_CompressStatement compressStatement;
	public @CHOICE Natural_ComputeStatement computeStatement;
	public @CHOICE Natural_DefineStatement defineStatement;
	public @CHOICE Natural_DisplayStatement displayStatement;
	public @CHOICE Natural_DivideStatement divideStatement;
	public @CHOICE Natural_DoStatement doStatement;
	public @CHOICE Natural_EndStatement endStatement;
	public @CHOICE Natural_EnterStatement enterStatement;
	public @CHOICE Natural_EscapeStatement escapeStatement;
	public @CHOICE Natural_FindStatement findStatement;
	public @CHOICE Natural_FormatStatement frmatStatement;
	public @CHOICE Natural_GetStatement getStatement;
	public @CHOICE Natural_HistogramStatement histogramStatement;
	public @CHOICE Natural_IfStatement ifStatement;
	public @CHOICE Natural_LimitStatement limitStatement;
	public @CHOICE Natural_InputStatement inputStatement;
	public @CHOICE Natural_MoveStatement moveStatement;
	public @CHOICE Natural_ReadStatement readStatement;
	public @CHOICE Natural_ReinputStatement reinputStatement;
	public @CHOICE Natural_RejectStatement rejectStatement;
	public @CHOICE Natural_ReleaseStatement releaseStatement;
	public @CHOICE Natural_RepeatStatement repeatStatement;
	public @CHOICE Natural_SkipStatement skipStatement;
	public @CHOICE Natural_SortStatement sortStatement;
	public @CHOICE Natural_StopStatement stopStatement;
	public @CHOICE Natural_StoreStatement storeStatement;
	public @CHOICE Natural_SuspendStatement suspendStatement;
	public @CHOICE Natural_UpdateStatement updateStatement;
	public @CHOICE Natural_WriteStatement writeStatement;
}