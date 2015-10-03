// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_UnlockStatement extends COBOL_AbstractStatement
{
	public COBOL_Keyword UNLOCK = new COBOL_Keyword("UNLOCK");
	public COBOL_Identifier_Reference file;
}
