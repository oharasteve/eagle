// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2015

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_InvokeStatement extends COBOL_AbstractStatement
{
	public COBOL_Keyword INVOKE = new COBOL_Keyword("INVOKE");
	public COBOL_Identifier_Reference object;
	public COBOL_Literal entryPoint;
	public @OPT COBOL_InvokeUsing using;
	public @OPT COBOL_InvokeReturning returning;
	
	public static class COBOL_InvokeUsing extends TokenSequence
	{
		public COBOL_Keyword USING = new COBOL_Keyword("USING");
		public @OPT COBOL_Keyword BY = new COBOL_Keyword("BY");
		public @OPT COBOL_Keyword VALUE = new COBOL_Keyword("VALUE");
		public COBOL_Expression expr;
		public @OPT COBOL_InvokeSize size;
		public @OPT TokenList<COBOL_InvokeMoreUsing> more;
		
		public static class COBOL_InvokeSize extends TokenSequence
		{
			public COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
			public COBOL_Number size;
		}
		
		public static class COBOL_InvokeMoreUsing extends TokenSequence
		{
			public PunctuationComma comma;
			public COBOL_Expression expr;
			public @OPT COBOL_InvokeSize size;
		}
	}
	
	public static class COBOL_InvokeReturning extends TokenSequence
	{
		public COBOL_Keyword RETURNING = new COBOL_Keyword("RETURNING");
		public COBOL_Identifier_Reference result;
	}
}
