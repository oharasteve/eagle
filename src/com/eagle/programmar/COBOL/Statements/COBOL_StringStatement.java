// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_StringStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsstri.htm") COBOL_Keyword STRING = new COBOL_Keyword("STRING");
	public TokenList<COBOL_StringWhat> elements;
	public COBOL_Keyword INTO = new COBOL_Keyword("INTO");
	public TokenList<COBOL_StringPiece> pieces;
	public @OPT COBOL_StringWith with;
	public @OPT COBOL_Keyword ENDSTRING = new COBOL_Keyword("END-STRING");
	
	public static class COBOL_StringWhat extends TokenSequence
	{
		public COBOL_Expression expr;
		public COBOL_Keyword DELIMITED1 = new COBOL_Keyword("DELIMITED");
		public @OPT COBOL_Keyword BY1 = new COBOL_Keyword("BY");
		public @OPT COBOL_HexOrLiteral delim;
		public @OPT COBOL_Keyword OR = new COBOL_Keyword("OR");
		public @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
		public @OPT COBOL_Keyword SPACES = new COBOL_Keyword("SPACES");
		public @OPT COBOL_Keyword SPACE = new COBOL_Keyword("SPACE");
		public @OPT COBOL_Keyword DELIMITED2 = new COBOL_Keyword("DELIMITED");
		public @OPT COBOL_Keyword BY2 = new COBOL_Keyword("BY");
		public @OPT COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
		
		public static class COBOL_HexOrLiteral extends TokenChooser
		{
			public @CHOICE COBOL_HexNumber hex;
			public @CHOICE COBOL_Literal literal;
		}
	}

	public static class COBOL_StringPiece extends TokenSequence
	{
		public @OPT PunctuationComma comma;
		public COBOL_Identifier_Reference intoVar;
		public @OPT COBOL_StringCount count;
		
		public static class COBOL_StringCount extends TokenSequence
		{
			public COBOL_Keyword COUNT = new COBOL_Keyword("COUNT");
			public COBOL_Keyword IN = new COBOL_Keyword("IN");
			public COBOL_Identifier_Reference countVar;
		}
	}
	
	public static class COBOL_StringWith extends TokenSequence
	{
		public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public COBOL_Keyword POINTER = new COBOL_Keyword("POINTER");
		public COBOL_Identifier_Reference withPointer;
	}
}
