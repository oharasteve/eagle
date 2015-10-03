// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Program_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_CommentToEndOfLine;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_IdentificationDivision extends TokenSequence
{
	public COBOL_KeywordChoice identification = new COBOL_KeywordChoice("IDENTIFICATION", "ID");
	public COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
	public COBOL_Punctuation dot1 = new COBOL_Punctuation('.');
	public @OPT COBOL_ProgramId programId;
	
	public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
	public @OPT COBOL_Keyword INITIAL = new COBOL_Keyword("INITIAL");
	public @OPT COBOL_Keyword PROGRAM = new COBOL_Keyword("PROGRAM");
	public @OPT COBOL_Punctuation dot4 = new COBOL_Punctuation('.');
	
	public @OPT TokenList<COBOL_Comment> comments1;

	public @OPT TokenList<COBOL_IdentificationEntry> entries;
	
	public @OPT TokenList<COBOL_Comment> comments2;
	
	public static class COBOL_ProgramId extends TokenSequence
	{
		public COBOL_Keyword PROGRAMID = new COBOL_Keyword("PROGRAM-ID");
		public @OPT COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public COBOL_Program_Definition programDef;
		public @OPT COBOL_Program_Subname subId;
	}
	
	public static class COBOL_IdentificationEntry extends TokenChooser
	{
		public static class COBOL_IdentificationSimple extends TokenSequence
		{
			public COBOL_KeywordChoice entryWord = new COBOL_KeywordChoice(
					"AUTHOR", "INSTALLATION", "DATE-WRITTEN", "DATE-COMPILED", "SECURITY");
			public @OPT COBOL_Punctuation dot = new COBOL_Punctuation('.');
			public TokenList<COBOL_CommentToEndOfLine> comments;
		}
				
		public static class COBOL_SpecialNames extends TokenSequence
		{
			public COBOL_Keyword SPECIALNAMES = new COBOL_Keyword("SPECIAL-NAMES");
			public COBOL_Punctuation dot1 = new COBOL_Punctuation('.');
			public COBOL_Keyword CALLCONVENTION = new COBOL_Keyword("CALL-CONVENTION");
			public COBOL_Number code;
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_Keyword WINAPI = new COBOL_Keyword("WINAPI");
			public COBOL_Punctuation dot2 = new COBOL_Punctuation('.');
		}
	}
	
	public static class COBOL_Program_Subname extends TokenSequence
	{
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public COBOL_Keyword CBL = new COBOL_Keyword("CBL");
	}
}
