// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 9, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_NMake_Statement extends TokenSequence
{
	public CMD_Keyword NMAKE = new CMD_Keyword("nmake");
	public TokenList<CMD_NMake_Parameter> params;
	
	public static class CMD_NMake_Parameter extends TokenChooser
	{
		public CMD_Argument target;

		public static class CMD_NMake_Option_I extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword I = new CMD_Keyword("i");
		}

		public static class CMD_NMake_Option_K extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword K = new CMD_Keyword("k");
		}

		public static class CMD_NMake_Option_E extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword E = new CMD_Keyword("e");
		}


		public static class CMD_NMake_Option_F extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword F = new CMD_Keyword("f");
			public CMD_Argument makefile;
		}
	}
}
