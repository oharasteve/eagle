// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_Rmdir_Statement extends TokenSequence
{
	public @DOC("rmdir.mspx") CMD_Keyword RMDIR = new CMD_Keyword("rmdir");
	public @OPT TokenList<CMD_Rmdir_Option> opts;
	public CMD_Argument dir;
	
	public static class CMD_Rmdir_Option extends TokenChooser
	{
		public @CHOICE static class CMD_Rmdir_Option_Q extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword Q = new CMD_Keyword("q");
		}

		public @CHOICE static class CMD_Rmdir_Option_S extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword S = new CMD_Keyword("s");
		}
	}
}
