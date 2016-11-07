// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class COBOL_SetStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsset.htm") COBOL_Keyword SET = new COBOL_Keyword("SET");
	public COBOL_Identifier_Reference id;
	public COBOL_SetHow how;
	public COBOL_Expression expr;
	
	public static class COBOL_SetHow extends TokenChooser
	{
		public @CHOICE COBOL_Keyword TO = new COBOL_Keyword("TO");

		public @CHOICE static class COBOL_SetBy extends TokenSequence
		{
			public COBOL_Keyword UP = new COBOL_Keyword("UP");
			public COBOL_Keyword BY = new COBOL_Keyword("BY");
		}
	}
}