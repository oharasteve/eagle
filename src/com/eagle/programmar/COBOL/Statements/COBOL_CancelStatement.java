// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;

public class COBOL_CancelStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpscanc.htm") COBOL_Keyword CANCEL = new COBOL_Keyword("CANCEL");
	public COBOL_CancelWhat what;
	
	public static class COBOL_CancelWhat extends TokenChooser
	{
		public @CHOICE COBOL_Literal literal;
		public @CHOICE COBOL_Identifier_Reference var;
	}
}
