// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Paragraph.COBOL_ParagraphHeader;
import com.eagle.programmar.COBOL.COBOL_ScreenSection.COBOL_ScreenDeclaration;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Paragraph_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Section_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_ProcedureDivision extends TokenSequence
{
	public @OPT TokenList<COBOL_Comment> comments;
	
	public COBOL_Keyword PROCEDURE = new COBOL_Keyword("PROCEDURE");
	public COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
	public @OPT COBOL_ProcedureUsing using;
	public @OPT COBOL_ProcedureChaining chaining;
	public @OPT COBOL_Keyword WINAPI = new COBOL_Keyword("WINAPI");
	public COBOL_Punctuation dot = new COBOL_Punctuation('.');
	public @OPT COBOL_Declaratives declaratives;
	public TokenList<COBOL_Section> sections;
	public @OPT COBOL_ParagraphHeader extraPara;
	
	public static class COBOL_ProcedureUsing extends TokenSequence
	{
		public COBOL_Keyword USING = new COBOL_Keyword("USING");
		public COBOL_Identifier_Reference id;
		public @OPT TokenList<COBOL_ProcedureUsingWhat> uses;

		public static class COBOL_ProcedureUsingWhat extends TokenSequence
		{
			public @OPT COBOL_Punctuation comma = new COBOL_Punctuation(',');
			public COBOL_Identifier_Reference id;
		}
	}

	public static class COBOL_ProcedureChaining extends TokenSequence
	{
		public COBOL_Keyword CHAINING = new COBOL_Keyword("CHAINING");
		public COBOL_Identifier_Reference id;
		public @OPT TokenList<COBOL_ProcedureChainingWhat> chain;

		public static class COBOL_ProcedureChainingWhat extends TokenSequence
		{
			public @OPT COBOL_Punctuation comma = new COBOL_Punctuation(',');
			public COBOL_Identifier_Reference id;
		}
	}

	public static class COBOL_Declaratives extends TokenSequence
	{
		public COBOL_Keyword DECLARATIVES1 = new COBOL_Keyword("DECLARATIVES");
		public COBOL_Punctuation dot1 = new COBOL_Punctuation('.');
		public COBOL_Section section;
		public COBOL_Keyword END = new COBOL_Keyword("END");
		public COBOL_Keyword DECLARATIVES2 = new COBOL_Keyword("DECLARATIVES");
		public COBOL_Punctuation dot2 = new COBOL_Punctuation('.');
	}
	
	public static class COBOL_Section extends TokenSequence
	{
		public @OPT COBOL_SectionHeader sectionHeader;
		public TokenList<COBOL_Paragraph> paragraphs;

		public static class COBOL_SectionHeader extends TokenSequence
		{
			public COBOL_Section_Definition sectionName;
			public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
			public @OPT COBOL_Number number;
			public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		}
	}

	public static class COBOL_Paragraph extends TokenSequence
	{
		public @OPT TokenList<COBOL_ParagraphHeader> paragraphHeaders;
		public TokenList<COBOL_SentenceOrComment> sentences;

		public static class COBOL_SentenceOrComment extends TokenChooser
		{
			public COBOL_Comment comment;
			public COBOL_Sentence sentence;
			public @LAST COBOL_ScreenDeclaration screen;

			public @LAST static class COBOL_DataInParagraph extends TokenSequence
			{
				public TokenList<COBOL_CopyOrDataDeclaration> data;
			}
		}
		
		public static class COBOL_ParagraphHeader extends TokenSequence
		{
			public COBOL_Paragraph_Definition paragraphName;
			public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		}
	}

	public static class COBOL_Sentence extends TokenSequence
	{
		public TokenList<COBOL_StatementOrComment> statements;
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public @CURIOUS("SENTENCE: Extra dot") @OPT COBOL_Punctuation dot2 = new COBOL_Punctuation('.');
		
		public static class COBOL_StatementOrComment extends TokenChooser
		{
			public COBOL_Comment comment;
			public COBOL_Statement statement;
		}
	}
}
