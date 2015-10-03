// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_OpenStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsopen.htm") COBOL_Keyword OPEN = new COBOL_Keyword("OPEN");
	public COBOL_KeywordChoice inputOutput = new COBOL_KeywordChoice("I-O", "INPUT", "OUTPUT", "EXTEND");
	public TokenList<COBOL_OpenFileList> fileList;
	
	public static class COBOL_OpenFileList extends TokenSequence
	{
		public @OPT COBOL_Punctuation comma = new COBOL_Punctuation(',');
		public COBOL_Identifier_Reference file;
	}
}
