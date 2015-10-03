// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Statements.COBOL_UnstringStatement.COBOL_UnstringOrClause.COBOL_UnstringOrWhat;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_UnstringStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsunst.htm") COBOL_Keyword UNSTRING = new COBOL_Keyword("UNSTRING");
	public COBOL_Expression expr;
	public COBOL_Keyword DELIMITED = new COBOL_Keyword("DELIMITED");
	public COBOL_Keyword BY = new COBOL_Keyword("BY");
	public COBOL_UnstringOrWhat delim;
	public @OPT COBOL_UnstringOrClause orClause;
	public COBOL_Keyword INTO = new COBOL_Keyword("INTO");
	public TokenList<COBOL_UnstringPiece> pieces;
	public @OPT COBOL_UnstringWith with;
	public @OPT COBOL_Keyword ENDUNSTRING = new COBOL_Keyword("END-UNSTRING");
	
	public static class COBOL_UnstringOrClause extends TokenSequence
	{
		public COBOL_Keyword OR = new COBOL_Keyword("OR");
		public COBOL_UnstringOrWhat unstringWhat;
		
		public static class COBOL_UnstringOrWhat extends TokenChooser
		{
			public COBOL_Literal delim;
			
			public static class COBOL_UnstringOrSpaces extends TokenSequence
			{
				public @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
				public COBOL_Keyword SPACES = new COBOL_Keyword("SPACES");
			}
		}
	}

	public static class COBOL_UnstringPiece extends TokenSequence
	{
		public @OPT COBOL_Punctuation comma = new COBOL_Punctuation(',');
		public COBOL_Identifier_Reference intoVar;
		public @OPT COBOL_UnstringCount count;
		
		public static class COBOL_UnstringCount extends TokenSequence
		{
			public COBOL_Keyword COUNT = new COBOL_Keyword("COUNT");
			public COBOL_Keyword IN = new COBOL_Keyword("IN");
			public COBOL_Identifier_Reference countVar;
		}
	}
	
	public static class COBOL_UnstringWith extends TokenSequence
	{
		public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public COBOL_Keyword POINTER = new COBOL_Keyword("POINTER");
		public COBOL_Identifier_Reference withPointer;
	}
}
