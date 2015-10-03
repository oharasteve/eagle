// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_RewriteStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsrewr.htm") COBOL_Keyword REWRITE = new COBOL_Keyword("REWRITE");
	public COBOL_Identifier_Reference file;
	public @OPT COBOL_RewriteKey key;
	public @OPT COBOL_Keyword ENDREWRITE = new COBOL_Keyword("END-REWRITE");

	public static class COBOL_RewriteKey extends TokenSequence
	{
		public COBOL_Keyword INVALID = new COBOL_Keyword("INVALID");
		public COBOL_Keyword KEY = new COBOL_Keyword("KEY");
		public TokenList<COBOL_Statement> statements;
	}
}
