// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_SortStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpssort.htm") COBOL_Keyword SORT = new COBOL_Keyword("SORT");
	public COBOL_Identifier_Reference file;
	public COBOL_Keyword ON = new COBOL_Keyword("ON");
	public COBOL_Keyword ASCENDING = new COBOL_Keyword("ASCENDING");
	public @OPT COBOL_Keyword KEY = new COBOL_Keyword("KEY");
	public COBOL_Identifier_Reference key;
	public @OPT TokenList<COBOL_SortKey> moreKeys;
	public COBOL_SortInput input;
	public @OPT COBOL_SortOutput output;
	public @OPT COBOL_SortGiving giving;

	public static class COBOL_SortKey extends TokenSequence
	{
		public @OPT COBOL_Punctuation comma = new COBOL_Punctuation(',');
		public COBOL_Identifier_Reference key;
	}
	
	public static class COBOL_SortInput extends TokenSequence
	{
		public COBOL_Keyword INPUT = new COBOL_Keyword("INPUT");
		public COBOL_Keyword PROCEDURE = new COBOL_Keyword("PROCEDURE");
		public COBOL_Keyword IS = new COBOL_Keyword("IS");
		public COBOL_Identifier_Reference inProc;
	}

	public static class COBOL_SortOutput extends TokenSequence
	{
		public COBOL_Keyword OUTPUT = new COBOL_Keyword("OUTPUT");
		public COBOL_Keyword PROCEDURE = new COBOL_Keyword("PROCEDURE");
		public COBOL_Keyword IS = new COBOL_Keyword("IS");
		public COBOL_Identifier_Reference outProc;
	}
	
	public static class COBOL_SortGiving extends TokenSequence
	{
		public COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public COBOL_Identifier_Reference giving;
	}
}
