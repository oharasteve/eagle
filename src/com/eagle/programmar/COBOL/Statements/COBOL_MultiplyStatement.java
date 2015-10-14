// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_MultiplyStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsmult.htm") COBOL_Keyword MULTIPLY = new COBOL_Keyword("MULTIPLY");
	public COBOL_Expression expr;
	public COBOL_Keyword BY = new COBOL_Keyword("BY");
	public COBOL_Expression expression;
	public @OPT COBOL_MultiplyGiving giving;
	public @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");
	public @OPT COBOL_MultiplyOnSizeError onError;

	public static class COBOL_MultiplyGiving extends TokenSequence
	{
		public COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public COBOL_Identifier_Reference var;
		public @OPT COBOL_Subscript subscript;
		public @OPT TokenList<COBOL_MultiplyMoreVars> moreVars;
		
		public static class COBOL_MultiplyMoreVars extends TokenSequence
		{
			public @OPT PunctuationComma comma;
			public COBOL_Identifier_Reference var;
			public @OPT COBOL_Subscript subscript;
		}
	}

	public static class COBOL_MultiplyOnSizeError extends TokenSequence
	{
		public COBOL_Keyword ON = new COBOL_Keyword("ON");
		public COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
		public COBOL_Keyword ERROR = new COBOL_Keyword("ERROR");
		public TokenList<COBOL_Statement> actions;
	}
}
