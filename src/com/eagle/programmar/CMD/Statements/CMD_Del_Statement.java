// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMD_Del_Statement extends TokenSequence
{
	public @DOC("del.mspx") CMD_Keyword DEL = new CMD_Keyword("del");
	public @OPT TokenList<CMD_Del_Option> opts;
	public TokenList<CMD_Argument> file;
	
	public static class CMD_Del_Option extends TokenChooser
	{
		public static class CMD_Del_Option_F extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Keyword F = new CMD_Keyword("f");
		}

		public static class CMD_Del_Option_Q extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Keyword Q = new CMD_Keyword("q");
		}

		public static class CMD_Del_Option_S extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Keyword S = new CMD_Keyword("s");
		}
	}
}
