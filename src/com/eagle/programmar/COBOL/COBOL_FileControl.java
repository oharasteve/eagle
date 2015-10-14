// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_FileControl extends TokenSequence
{
	public COBOL_Keyword FILECONTROL = new COBOL_Keyword("FILE-CONTROL");
	public PunctuationPeriod dot;
	public @OPT TokenList<COBOL_Copy_or_FileSelect> fileSelects;
	
	public static class COBOL_Copy_or_FileSelect extends TokenChooser
	{
		public COBOL_Copy_Directive copyDirective;
		public COBOL_Comment comment;
		public COBOL_Directive directive;
		
		public static class COBOL_FileSelect extends TokenSequence
		{
			public COBOL_Keyword SELECT = new COBOL_Keyword("SELECT");
			public @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
			public @OPT COBOL_Keyword OPTIONAL = new COBOL_Keyword("OPTIONAL");
			public COBOL_Identifier_Reference selectWhat;
			public COBOL_Keyword ASSIGN = new COBOL_Keyword("ASSIGN");
			public @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
			public COBOL_AssignTo assignTo;
			public @OPT TokenList<COBOL_SelectClause> selectClauses;
			public PunctuationPeriod dot;
		}
	}
	
	public static class COBOL_AssignTo extends TokenChooser
	{
		public COBOL_Literal assignLiteral;
		public COBOL_Keyword DISK = new COBOL_Keyword("DISK");
		public COBOL_Identifier_Reference dataRef;
	}
	
	public static class COBOL_SelectClause extends TokenChooser
	{
		public COBOL_Comment comment;
		
		public static class COBOL_SelectOrganization extends TokenSequence
		{
			public COBOL_Keyword ORGANIZATION = new COBOL_Keyword("ORGANIZATION");
			public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @OPT COBOL_Keyword LINE = new COBOL_Keyword("LINE");
			public COBOL_KeywordChoice organization = new COBOL_KeywordChoice(
					"INDEXED", "SEQUENTIAL", "RELATIVE");
		}
		
		public static class COBOL_SelectAccess extends TokenSequence
		{
			public COBOL_Keyword ACCESS = new COBOL_Keyword("ACCESS");
			public @OPT COBOL_Keyword MODE = new COBOL_Keyword("MODE");
			public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_KeywordChoice access = new COBOL_KeywordChoice(
					"SEQUENTIAL", "DYNAMIC", "RANDOM");
		}
		public static class COBOL_SelectRecord extends TokenSequence
		{
			public COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
			public @OPT COBOL_Keyword KEY = new COBOL_Keyword("KEY");
			public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_Identifier_Reference id;
			public @OPT COBOL_SelectRecordEquals selectEquals;
			
			public static class COBOL_SelectRecordEquals extends TokenSequence
			{
				public PunctuationEquals equals;
				public TokenList<COBOL_Identifier_Reference> ids;
			}
		}
		
		public static class COBOL_SelectAlternate extends TokenSequence
		{
			public COBOL_Keyword ALTERNATE = new COBOL_Keyword("ALTERNATE");
			public @OPT COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
			public @OPT COBOL_Keyword KEY = new COBOL_Keyword("KEY");
			public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_Identifier_Reference id;
			public @OPT COBOL_SelectAlternates selectAlternates;
			public @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
			public @OPT COBOL_Keyword DUPLICATES = new COBOL_Keyword("DUPLICATES");
			
			public static class COBOL_SelectAlternates extends TokenSequence
			{
				public PunctuationEquals equals;
				public TokenList<COBOL_Identifier_Reference> variables;
			}
		}
		
		public static class COBOL_SelectFile extends TokenSequence
		{
			public @OPT COBOL_Keyword FILE = new COBOL_Keyword("FILE");
			public COBOL_Keyword STATUS = new COBOL_Keyword("STATUS");
			public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_Identifier_Reference id;
		}

		public static class COBOL_SelectRelative extends TokenSequence
		{
			public COBOL_Keyword RELATIVE = new COBOL_Keyword("RELATIVE");
			public @OPT COBOL_Keyword KEY = new COBOL_Keyword("KEY");
			public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_Identifier_Reference id;
		}
		
		//LOCK MANUAL WITH LOCK ON MULTIPLE RECORDS
		public static class COBOL_SelectLock extends TokenSequence
		{
			public COBOL_KeywordChoice LOCK = new COBOL_KeywordChoice("LOCK", "LOCKING");
			public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_KeywordChoice EXCLUSIVE = new COBOL_KeywordChoice("EXCLUSIVE", "AUTOMATIC", "MANUAL");
			public @OPT COBOL_SelectLockWith lockWith;
			
			public static class COBOL_SelectLockWith extends TokenSequence
			{
				public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
				public COBOL_Keyword LOCK = new COBOL_Keyword("LOCK");
				public COBOL_Keyword ON = new COBOL_Keyword("ON");
				public COBOL_Keyword MULTIPLE = new COBOL_Keyword("MULTIPLE");
				public COBOL_Keyword RECORDS = new COBOL_Keyword("RECORDS");
			}
		}
	}
}
