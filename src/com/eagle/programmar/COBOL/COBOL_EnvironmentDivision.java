// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Class_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_EnvironmentDivision extends TokenSequence
{
	public COBOL_Keyword ENVIRONMENT = new COBOL_Keyword("ENVIRONMENT");
	public COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
	public COBOL_Punctuation dot = new COBOL_Punctuation('.');
	public @OPT COBOL_ClassControl classControl;
	public @OPT COBOL_ConfigurationSection configuration;
	public @OPT COBOL_InputOutputSection inputOutput;
	public @OPT COBOL_SpecialNames specialNames;
	
	public static class COBOL_ClassControl extends TokenSequence
	{
		public COBOL_Keyword CLASSCONTROL = new COBOL_Keyword("CLASS-CONTROL");
		public COBOL_Punctuation dot1 = new COBOL_Punctuation('.');
		public TokenList<COBOL_ClassControlIs> controlIsList;
		public COBOL_Punctuation dot2 = new COBOL_Punctuation('.');
		
		public static class COBOL_ClassControlIs extends TokenSequence
		{
			public COBOL_Class_Definition classDef;
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
			public COBOL_Keyword CLASS = new COBOL_Keyword("CLASS");
			public COBOL_Literal name;
		}
	}
	
	public static class COBOL_ConfigurationSection extends TokenSequence
	{
		public COBOL_Keyword CONFIGURATION = new COBOL_Keyword("CONFIGURATION");
		public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public @OPT COBOL_SpecialNames specialNames;
	}
	
	public static class COBOL_InputOutputSection extends TokenSequence
	{
		public COBOL_Keyword INPUTOUTPUT = new COBOL_Keyword("INPUT-OUTPUT");
		public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public @OPT COBOL_FileControl fileControl;
		public @OPT COBOL_IOControl ioControl;
	}
	
	public static class COBOL_IOControl extends TokenSequence
	{
		public COBOL_Keyword IOCONTROL = new COBOL_Keyword("I-O-CONTROL");
		public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		public COBOL_IOControlSame controlSame;

		public static class COBOL_IOControlSame extends TokenSequence
		{
			public COBOL_Keyword SAME = new COBOL_Keyword("SAME");
			public COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
			public COBOL_Keyword AREA = new COBOL_Keyword("AREA");
			public COBOL_Keyword FOR = new COBOL_Keyword("FOR");
			public COBOL_Keyword NETWORK = new COBOL_Keyword("NETWORK");
			public COBOL_Keyword SHARED = new COBOL_Keyword("SHARED");
			public COBOL_Punctuation dot = new COBOL_Punctuation('.');
		}
	}
}
