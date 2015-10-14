// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_AddStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsadd.htm") COBOL_Keyword ADD = new COBOL_Keyword("ADD");
	public COBOL_Expression expr;
	public @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
	public @OPT COBOL_AddTo addTo;
	public @OPT COBOL_AddGiving giving;
	public @OPT TokenList<COBOL_AddOnSizeError> onErrorList;
	public @OPT COBOL_Keyword ENDADD = new COBOL_Keyword("END-ADD");
	
	public static class COBOL_AddMoreExprs extends TokenSequence
	{
		public PunctuationComma comma;
		public COBOL_Expression expr;
	}

	public static class COBOL_AddTo extends TokenSequence
	{
		public @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
		public COBOL_Variable var;
		public @OPT TokenList<COBOL_AddMoreVars> moreVars;
		
		public static class COBOL_AddMoreVars extends TokenSequence
		{
			public @OPT PunctuationComma comma;
			public COBOL_Variable var;
		}
	}
	
	public static class COBOL_AddGiving extends TokenSequence
	{
		public @OPT @CURIOUS("ADD: Extra comma") PunctuationComma comma;
		public COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public TokenList<COBOL_Identifier_Reference> sum;
		public @OPT COBOL_Subscript subscript;
	}

	public static class COBOL_AddOnSizeError extends TokenSequence
	{
		public @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public COBOL_Keyword ON = new COBOL_Keyword("ON");
		public COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
		public COBOL_Keyword ERROR = new COBOL_Keyword("ERROR");
		public TokenList<COBOL_Statement> actions;
	}
}
