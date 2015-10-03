// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_InitiateStatement extends COBOL_AbstractStatement
{
	public COBOL_Keyword INITIATE = new COBOL_Keyword("INITIATE");
	public COBOL_Identifier_Reference report;
}
