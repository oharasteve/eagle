// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_ReturnStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsretu.htm") COBOL_Keyword RETURN = new COBOL_Keyword("RETURN");
	public COBOL_Identifier_Reference file;
	public COBOL_ReturnAtEndAction atEnd;
	public COBOL_Keyword ENDRETURN = new COBOL_Keyword("END-RETURN");

	public static class COBOL_ReturnAtEndAction extends TokenSequence
	{
		public COBOL_Keyword AT = new COBOL_Keyword("AT");
		public COBOL_Keyword END = new COBOL_Keyword("END");
		public TokenList<COBOL_Statement> endAction;
	}
}
