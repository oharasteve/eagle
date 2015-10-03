// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 12, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_GoStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsgoto.htm") COBOL_Keyword GO = new COBOL_Keyword("GO");
	public @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
	public @OPT COBOL_Identifier_Reference label;
}
