// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 12, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_CallStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpscall.htm") COBOL_Keyword CALL = new COBOL_Keyword("CALL");
	public @OPT COBOL_KeywordChoice how = new COBOL_KeywordChoice("STATICCOBOL", "WINAPI");
	public COBOL_CallWhat callWhat;
	public @OPT COBOL_Keyword USING = new COBOL_Keyword("USING");
	public @OPT TokenList<COBOL_CallParameter> parameters;
	public @OPT COBOL_CallException exception;
	public @OPT COBOL_Keyword ENDCALL = new COBOL_Keyword("END-CALL");
	
	public static class COBOL_CallWhat extends TokenChooser
	{
		public @CHOICE COBOL_Literal callFunction;
		public @CHOICE COBOL_HexNumber callHex;
		public @CHOICE COBOL_Identifier_Reference callVariable;
	}
	
	public static class COBOL_CallParameter extends TokenSequence
	{
		public @OPT PunctuationComma comma;
		public @OPT COBOL_Keyword BY = new COBOL_Keyword("BY");
		public @OPT COBOL_KeywordChoice byHow = new COBOL_KeywordChoice(
				"CONTENT", "REFERENCE", "VALUE");
		public COBOL_Expression expression;
		public @OPT COBOL_ValueSize size;
		
		public static class COBOL_ValueSize extends TokenSequence
		{
			public COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
			public COBOL_Number size;
		}
	}
	
	public static class COBOL_CallException extends TokenSequence
	{
		public COBOL_Keyword ON = new COBOL_Keyword("ON");
		public COBOL_Keyword EXCEPTION = new COBOL_Keyword("EXCEPTION");
		public TokenList<COBOL_Statement> statements;
	}
}
