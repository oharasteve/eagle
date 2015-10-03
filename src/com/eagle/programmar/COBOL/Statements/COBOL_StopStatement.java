// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_StopStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsstop.htm") COBOL_Keyword STOP = new COBOL_Keyword("STOP");
	public COBOL_Keyword RUN = new COBOL_Keyword("RUN");
}
