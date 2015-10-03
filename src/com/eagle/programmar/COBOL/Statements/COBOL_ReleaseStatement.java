// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_ReleaseStatement extends COBOL_AbstractStatement
{
	public @DOC("rele.htm") COBOL_Keyword RELEASE = new COBOL_Keyword("RELEASE");
	public COBOL_Identifier_Reference id1;
	public @OPT COBOL_ReleaseFrom from;
	
	public static class COBOL_ReleaseFrom extends TokenSequence
	{
		public COBOL_Keyword FROM = new COBOL_Keyword("FROM");
		public COBOL_Identifier_Reference id2;
	}
}
