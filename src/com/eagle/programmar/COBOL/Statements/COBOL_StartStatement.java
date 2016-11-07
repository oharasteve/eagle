// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_StartStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsstar.htm") COBOL_Keyword START = new COBOL_Keyword("START");
	public COBOL_Identifier_Reference file;
	public COBOL_Keyword KEY1 = new COBOL_Keyword("KEY");
	public COBOL_StartRelOp oper;
	public COBOL_Identifier_Reference value;
	public @OPT COBOL_StartInvalid invalid;
	public @OPT COBOL_Keyword ENDSTART = new COBOL_Keyword("END-START");
	
	public static class COBOL_StartRelOp extends TokenChooser
	{
		public @CHOICE COBOL_PunctuationChoice operator = new COBOL_PunctuationChoice("<=", ">=", "<", ">");
		
		public @CHOICE static class COBOL_StartOper2 extends TokenSequence
		{
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_Keyword GREATER = new COBOL_Keyword("GREATER");
			public COBOL_Keyword THAN = new COBOL_Keyword("THAN");
		}
	}
	
	public static class COBOL_StartInvalid extends TokenSequence
	{
		public COBOL_Keyword INVALID = new COBOL_Keyword("INVALID");
		public COBOL_Keyword KEY2 = new COBOL_Keyword("KEY");
		public TokenList<COBOL_Statement> statements;
	}
}
