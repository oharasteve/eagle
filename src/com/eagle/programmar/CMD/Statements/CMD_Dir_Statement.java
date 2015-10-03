// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class CMD_Dir_Statement extends TokenSequence
{
	public @DOC("dir.mspx") CMD_Keyword DIR = new CMD_Keyword("dir");
	public @OPT TokenList<CMD_Dir_Option> opts;
	public TokenList<CMD_Argument> file;
	
	public static class CMD_Dir_Option extends TokenChooser
	{
		public static class CMD_Dir_Option_A extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Keyword A = new CMD_Keyword("a");
			public CMD_Punctuation colon = new CMD_Punctuation(':');
			public CMD_KeywordChoice R = new CMD_KeywordChoice("h", "r");
		}

		public static class CMD_Dir_Option_B extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Keyword B = new CMD_Keyword("b");
		}

		public static class CMD_Dir_Option_O extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Keyword O = new CMD_Keyword("o");
			public CMD_Punctuation colon = new CMD_Punctuation(':');
			public CMD_Keyword D = new CMD_Keyword("d");
		}

		public static class CMD_Dir_Option_S extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Keyword S = new CMD_Keyword("s");
		}
	}
}
