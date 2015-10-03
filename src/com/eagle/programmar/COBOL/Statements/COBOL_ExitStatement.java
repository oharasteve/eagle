// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_ExitStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsexit.htm") COBOL_Keyword EXIT = new COBOL_Keyword("EXIT");
	public @OPT COBOL_Keyword PROGRAM = new COBOL_Keyword("PROGRAM");
}
