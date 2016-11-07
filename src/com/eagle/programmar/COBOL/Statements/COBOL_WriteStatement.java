// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_WriteStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpswrit.htm") COBOL_Keyword WRITE = new COBOL_Keyword("WRITE");
	public COBOL_Identifier_Reference file;
	public @OPT COBOL_WriteFrom from;
	public @OPT COBOL_WriteAfter after;
	public @OPT COBOL_WriteKey key;
	public @OPT COBOL_Keyword ENDWRITE = new COBOL_Keyword("END-WRITE");
	
	public static class COBOL_WriteFrom extends TokenSequence
	{
		public COBOL_Keyword FROM = new COBOL_Keyword("FROM");
		public COBOL_WriteFromWhat fromWhat;
		
		public static class COBOL_WriteFromWhat extends TokenChooser
		{
			public @CHOICE COBOL_Identifier_Reference id;
			public @CHOICE COBOL_Keyword SPACES = new COBOL_Keyword("SPACES");
		}
	}
	
	public static class COBOL_WriteAfter extends TokenSequence
	{
		public COBOL_KeywordChoice after = new COBOL_KeywordChoice("BEFORE", "AFTER");
		public @OPT COBOL_Keyword ADVANCING = new COBOL_Keyword("ADVANCING");
		public @OPT COBOL_Expression lines;
		public @OPT COBOL_KeywordChoice what = new COBOL_KeywordChoice(
				"PAGE", "LINE", "LINES");
	}

	public static class COBOL_WriteKey extends TokenSequence
	{
		public COBOL_Keyword INVALID = new COBOL_Keyword("INVALID");
		public COBOL_Keyword KEY = new COBOL_Keyword("KEY");
		public TokenList<COBOL_Statement> statements;
	}
}
