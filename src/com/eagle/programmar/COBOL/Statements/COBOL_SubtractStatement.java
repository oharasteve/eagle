// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_SubtractStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpssubt.htm") COBOL_Keyword SUBTRACT = new COBOL_Keyword("SUBTRACT");
	public COBOL_Expression expr;
	public COBOL_SubtractFrom from;
	public @OPT COBOL_SubtractGiving giving;
	
	public static class COBOL_SubtractGiving extends TokenSequence
	{
		public COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public COBOL_Variable result;
		public @OPT COBOL_Subscript subscript;
		public @OPT TokenList<COBOL_SubtractMoreVars> moreVars;
	}
	
	public static class COBOL_SubtractFrom extends TokenSequence
	{
		public COBOL_Keyword FROM = new COBOL_Keyword("FROM");
		public COBOL_Expression expr;
		public @OPT TokenList<COBOL_SubtractMoreVars> moreVars;
	}
	
	public static class COBOL_SubtractMoreVars extends TokenSequence
	{
		public @OPT COBOL_Punctuation comma = new COBOL_Punctuation(',');
		public COBOL_Variable var;
	}
}
