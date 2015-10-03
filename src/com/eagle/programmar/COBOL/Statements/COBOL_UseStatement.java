// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_UseStatement extends COBOL_AbstractStatement
{
	public COBOL_Keyword USE = new COBOL_Keyword("USE");
	public COBOL_Keyword BEFORE = new COBOL_Keyword("BEFORE");
	public COBOL_Keyword REPORTING = new COBOL_Keyword("REPORTING");
	public COBOL_Identifier_Reference id;
}
