// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_SpecialNames extends TokenSequence
{
	public COBOL_Keyword SPECIALNAMES = new COBOL_Keyword("SPECIAL-NAMES");
	public COBOL_Punctuation dot1 = new COBOL_Punctuation('.');
	public TokenList<COBOL_SpecialName> specialNames;
	public COBOL_Punctuation dot2 = new COBOL_Punctuation('.');

	public static class COBOL_SpecialName extends TokenChooser
	{
		public static class COBOL_SpecialNameCursor extends TokenSequence
		{
			public COBOL_Keyword CURSOR = new COBOL_Keyword("CURSOR");
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_Identifier_Reference var;
		}

		public static class COBOL_SpecialNameConsole extends TokenSequence
		{
			public COBOL_Keyword CONSOLE = new COBOL_Keyword("CONSOLE");
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_Keyword CRT = new COBOL_Keyword("CRT");
		}

		public static class COBOL_SpecialNameCrtStatus extends TokenSequence
		{
			public COBOL_Keyword CRT = new COBOL_Keyword("CRT");
			public COBOL_Keyword STATUS = new COBOL_Keyword("STATUS");
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_Identifier_Reference var;
		}

		public static class COBOL_SpecialNameCallConvention extends TokenSequence
		{
			public COBOL_Keyword CALL_CONVENTION = new COBOL_Keyword("CALL-CONVENTION");
			public COBOL_Number number;
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_KeywordChoice convention = new COBOL_KeywordChoice("STATICCOBOL", "STATICW32API");
		}
	}
}
