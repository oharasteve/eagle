// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_ReadStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsread.htm") COBOL_Keyword READ = new COBOL_Keyword("READ");
	public TokenList<COBOL_Identifier_Reference> files;
	public @OPT COBOL_ReadInto into;
	public @OPT COBOL_ReadNext next;
	public @OPT COBOL_ReadAtEndAction atEnd;
	public @OPT COBOL_ReadIgnoreLock ignoreLock;
	public @OPT COBOL_ReadKey key;
	public @OPT COBOL_ReadInvalidKey invalid;
	public @OPT COBOL_Keyword ENDREAD = new COBOL_Keyword("END-READ");

	public static class COBOL_ReadInto extends TokenSequence
	{
		public COBOL_Keyword INTO = new COBOL_Keyword("INTO");
		public COBOL_Identifier_Reference var;
	}
	
	public static class COBOL_ReadNext extends TokenSequence
	{
		public COBOL_Keyword NEXT = new COBOL_Keyword("NEXT");
		public @OPT COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
	}
	
	public static class COBOL_ReadKey extends TokenSequence
	{
		public @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public COBOL_Keyword KEY = new COBOL_Keyword("KEY");
		public COBOL_Keyword IS = new COBOL_Keyword("IS");
		public COBOL_Identifier_Reference value;
	}

	public static class COBOL_ReadInvalidKey extends TokenSequence
	{
		public COBOL_Keyword INVALID = new COBOL_Keyword("INVALID");
		public COBOL_Keyword KEY = new COBOL_Keyword("KEY");
		public TokenList<COBOL_Statement> statements;
	}

	public static class COBOL_ReadAtEndAction extends TokenSequence
	{
		public COBOL_Keyword AT = new COBOL_Keyword("AT");
		public COBOL_Keyword END = new COBOL_Keyword("END");
		public TokenList<COBOL_Statement> endAction;
	}
	
	public static class COBOL_ReadIgnoreLock extends TokenSequence
	{
		public @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @OPT COBOL_KeywordChoice IGNORE = new COBOL_KeywordChoice("IGNORE", "KEPT");
		public COBOL_Keyword LOCK = new COBOL_Keyword("LOCK");
	}
}
