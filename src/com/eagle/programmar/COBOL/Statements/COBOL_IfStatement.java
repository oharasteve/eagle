// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Sentence.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_IfStatement extends COBOL_AbstractStatement
{
	public COBOL_Keyword IF = new COBOL_Keyword("IF");
	public COBOL_Expression cond;
	public @OPT COBOL_Keyword THEN = new COBOL_Keyword("THEN");
	public TokenList<COBOL_StatementOrComment> thenActions;
	public @OPT COBOL_Else elseClause;
	public @OPT COBOL_Keyword ENDIF = new COBOL_Keyword("END-IF");
	
	public static class COBOL_Else extends TokenSequence
	{
		public COBOL_Keyword ELSE = new COBOL_Keyword("ELSE");
		public TokenList<COBOL_StatementOrComment> elseActions;
	}
}
