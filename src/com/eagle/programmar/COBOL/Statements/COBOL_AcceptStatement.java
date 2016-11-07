// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_AcceptStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpsacce.htm") COBOL_Keyword ACCEPT = new COBOL_Keyword("ACCEPT");
	public @OPT COBOL_AcceptPosition position;
	public COBOL_Identifier_Reference var;
	public @OPT COBOL_Subscript subscript;
	public @OPT TokenList<COBOL_AcceptOption> options;
	
	public static class COBOL_AcceptPosition extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public COBOL_Expression line;
		public PunctuationComma comma;
		public COBOL_Expression column;
		public PunctuationRightParen rightParen;
	}
	
	public static class COBOL_AcceptOption extends TokenChooser
	{
		public @CHOICE COBOL_KeywordChoice option = new COBOL_KeywordChoice(
				"AUTO",
				"AUTO-SKIP",
				"FULL",
				"NO-ECHO",
				"PROMPT",
				"SECURE",
				"UPDATE"
				);

		public @CHOICE static class COBOL_AcceptFrom extends TokenSequence
		{
			public COBOL_Keyword FROM = new COBOL_Keyword("FROM");
			public COBOL_KeywordChoice time = new COBOL_KeywordChoice("DATE", "DAY", "TIME");
			public @OPT COBOL_KeywordChoice format = new COBOL_KeywordChoice("YYYYMMDD", "YYYYDDD");
		}

		public @CHOICE static class COBOL_AcceptCommand extends TokenSequence
		{
			public COBOL_Keyword FROM = new COBOL_Keyword("FROM");
			public COBOL_Keyword COMMANDLINE = new COBOL_Keyword("COMMAND-LINE");
		}

		public @CHOICE static class COBOL_AcceptAt extends TokenSequence
		{
			public COBOL_Keyword AT = new COBOL_Keyword("AT");
			public @OPT COBOL_Keyword LINE = new COBOL_Keyword("LINE");
			public COBOL_Expression location;
		}
			
		public @CHOICE static class COBOL_AcceptColumn extends TokenSequence
		{
			public COBOL_Keyword COLUMN = new COBOL_Keyword("COLUMN");
			public COBOL_Expression column;
		}
		
		public @CHOICE static class COBOL_AcceptWithColors extends TokenSequence
		{
			public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
			public @OPT COBOL_Keyword UPDATE = new COBOL_Keyword("UPDATE");
			public TokenList<COBOL_AcceptColor> colors;
			
			public static class COBOL_AcceptColor extends TokenSequence
			{
				public COBOL_KeywordChoice color = new COBOL_KeywordChoice(
						"AUTO", "AUTO-SKIP", "FOREGROUND-COLOR", "BACKGROUND-COLOR", "HIGHLIGHT");
				public @OPT COBOL_Number fg;
			}
		}
	}
}
