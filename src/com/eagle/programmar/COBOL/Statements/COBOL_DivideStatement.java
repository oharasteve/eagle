// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class COBOL_DivideStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsdivi.htm") COBOL_Keyword DIVIDE = new COBOL_Keyword("DIVIDE");
	public COBOL_Expression y;
	public COBOL_KeywordChoice BYINTO = new COBOL_KeywordChoice("BY", "INTO");
	public COBOL_Expression x;
	public @OPT COBOL_DivideGiving giving;
	public @OPT COBOL_DivideRemainder remainder;
	public @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");
	
	public static class COBOL_DivideGiving extends TokenSequence
	{
		public COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public COBOL_Identifier_Reference quotient;
	}
	
	public static class COBOL_DivideRemainder extends TokenSequence
	{
		public COBOL_Keyword REMAINDER = new COBOL_Keyword("REMAINDER");
		public COBOL_Identifier_Reference remainder;
	}
}
