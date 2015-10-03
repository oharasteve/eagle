// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_File_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_DataDivision extends TokenSequence
{
	public @OPT COBOL_DataDivisionHeader header;
	public TokenList<COBOL_DataSection> sections;
	
	public static class COBOL_DataDivisionHeader extends TokenSequence
	{
		public COBOL_Keyword DATA = new COBOL_Keyword("DATA");
		public COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
	}

	public static class COBOL_DataSection extends TokenChooser
	{
		public COBOL_Comment comment;
		public COBOL_FileSection fileSection;
		public COBOL_WorkingStorageSection workingStorageSection;
		public COBOL_LocalStorageSection localStorageSection;
		public COBOL_ScreenSection screenSection;
		public COBOL_LinkageSection linkageSection;
		public COBOL_ReportSection reportSection;
	}
	
	public static class COBOL_FileSection extends TokenSequence
	{
		public COBOL_Keyword FILE = new COBOL_Keyword("FILE");
		public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public TokenList<COBOL_Copy_or_FileDescriptor> fileDescriptors;
	}
	
	public static class COBOL_Copy_or_FileDescriptor extends TokenChooser
	{
		public COBOL_Copy_Directive copyDirective;
		public COBOL_Comment comment;

		public static class COBOL_FileDescriptor extends TokenSequence
		{
			public COBOL_KeywordChoice fd_sd = new COBOL_KeywordChoice("FD", "SD");
			public COBOL_File_Definition id;
			public @OPT COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
			public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @OPT COBOL_Keyword EXTERNAL = new COBOL_Keyword("EXTERNAL");
			public @OPT COBOL_Identifier_Reference reportId;
			public @OPT COBOL_FD_LabelRecordStandard labelRecordStandard;
			public @OPT COBOL_FD_LabelRecordOmitted labelRecordOmitted;
			public @OPT COBOL_FD_RecordingMode recordingMode;
			public COBOL_Punctuation dot = new COBOL_Punctuation('.');
			public @OPT TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
			
			public static class COBOL_FD_LabelRecordStandard extends TokenSequence
			{
				// LABEL RECORD STANDARD VALUE OF FILE-ID "HLP\APACHELP.APC"
				public COBOL_Keyword LABEL = new COBOL_Keyword("LABEL");
				public COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
				public @OPT COBOL_Keyword IS1 = new COBOL_Keyword("IS");
				public COBOL_Keyword STANDARD = new COBOL_Keyword("STANDARD");
				public COBOL_Keyword VALUE = new COBOL_Keyword("VALUE");
				public COBOL_Keyword OF = new COBOL_Keyword("OF");
				public COBOL_Keyword FILEID = new COBOL_Keyword("FILE-ID");
				public @OPT COBOL_Keyword IS2 = new COBOL_Keyword("IS");
				public COBOL_FileId fileId;
				
				public static class COBOL_FileId extends TokenChooser
				{
					public COBOL_Identifier_Reference fileRef;
					public COBOL_Literal fileName;
				}
			}
			
			public static class COBOL_FD_RecordingMode extends TokenSequence
			{
				public COBOL_Keyword RECORDING = new COBOL_Keyword("RECORDING");
				public COBOL_Keyword MODE = new COBOL_Keyword("MODE");
				public COBOL_Keyword IS = new COBOL_Keyword("IS");
				public COBOL_Keyword VARIABLE = new COBOL_Keyword("VARIABLE");
			}

			public static class COBOL_FD_LabelRecordOmitted extends TokenSequence
			{
				// LABEL RECORD STANDARD VALUE OF FILE-ID "HLP\APACHELP.APC"
				public COBOL_Keyword LABEL = new COBOL_Keyword("LABEL");
				public COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
				public COBOL_Keyword OMITTED = new COBOL_Keyword("OMITTED");
				public @OPT COBOL_FD_Linage linage;
				
				public static class COBOL_FD_Linage extends TokenSequence
				{
					public COBOL_Keyword LINAGE = new COBOL_Keyword("LINAGE");
					public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
					public COBOL_Expression linage;
				}
			}
		}
	}
	
	public static class COBOL_WorkingStorageSection extends TokenSequence
	{
		public COBOL_Keyword WORKINGSTORAGE = new COBOL_Keyword("WORKING-STORAGE");
		public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
	}
		
	public static class COBOL_LocalStorageSection extends TokenSequence
	{
		public COBOL_Keyword LOCALSTORAGE = new COBOL_Keyword("LOCAL-STORAGE");
		public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public @OPT TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
	}
		
	public static class COBOL_LinkageSection extends TokenSequence
	{
		public COBOL_Keyword LINKAGE = new COBOL_Keyword("LINKAGE");
		public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
	}
	
	public static class COBOL_ReportSection extends TokenSequence
	{
		public COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
		public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public TokenList<COBOL_ReportEntry> reportEntries;
	}
	
	public static class COBOL_CopyOrDataDeclaration extends TokenChooser
	{
		public COBOL_Copy_Directive copyBook;
		public COBOL_DataDeclaration declaration;
	}
}
