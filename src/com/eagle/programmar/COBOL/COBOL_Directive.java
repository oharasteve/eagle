// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Terminals.COBOL_Identifier;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_Directive extends TokenSequence
{
	public COBOL_Punctuation dollar = new COBOL_Punctuation('$');
	public COBOL_WhichDirective which;
	
	public static class COBOL_WhichDirective extends TokenChooser
	{
		public @CHOICE COBOL_Keyword END = new COBOL_Keyword("END");

		public @CHOICE static class COBOL_IfDefinedDirective extends TokenSequence
		{
			public COBOL_Keyword IF = new COBOL_Keyword("IF");
			public COBOL_Identifier var;
			public COBOL_Keyword NOT = new COBOL_Keyword("NOT");
			public COBOL_Keyword DEFINED = new COBOL_Keyword("DEFINED");
		}
		
		public @LAST static class COBOL_IfExprDirective extends TokenSequence
		{
			public COBOL_Keyword IF = new COBOL_Keyword("IF");
			public COBOL_Expression expr;
		}
		
		public @CHOICE static class COBOL_SetDirective extends TokenSequence
		{
			public COBOL_Keyword SET = new COBOL_Keyword("SET");
			public TokenList<COBOL_SetWhat> sets;
			
			public static class COBOL_SetWhat extends TokenChooser
			{
				public @CHOICE COBOL_SetString setString;
				public @CHOICE COBOL_SetParensPlus setParensPlus;
				public @CHOICE COBOL_SetParensNumber setParensNumber;
			}
			
			public static class COBOL_SetString extends TokenSequence
			{
				public COBOL_KeywordChoice key = new COBOL_KeywordChoice(
						"ANS85", "CASE", "CONSTANT", "DATALIT", "DEFAULTBYTE", "KEYCOMPRESS", "LINKCOUNT", "MF", "MFOO",
						"NESTCALL", "NOALTER", "NOKEYCOMPRESS", "NOOSVS", "NOQUAL", "NOVSC2", "QUAL", "SOURCEFORMAT");
				public @OPT COBOL_Literal val;
			}
			
			public static class COBOL_SetParensPlus extends TokenSequence
			{
				public COBOL_KeywordChoice key = new COBOL_KeywordChoice("OOCTRL", "PREPROCESS", "REMOVE");
				public PunctuationLeftParen leftParen;
				public @OPT COBOL_Punctuation plus = new COBOL_Punctuation('+');
				public COBOL_KeywordChoice code = new COBOL_KeywordChoice("CONTROL", "P", "WINDOW1");
				public PunctuationRightParen rightParen;
			}

			public static class COBOL_SetParensNumber extends TokenSequence
			{
				public COBOL_KeywordChoice key = new COBOL_KeywordChoice("INTCODE");
				public PunctuationLeftParen leftParen;
				public COBOL_Number number;
				public PunctuationRightParen rightParen;
			}
		}
	}
}
