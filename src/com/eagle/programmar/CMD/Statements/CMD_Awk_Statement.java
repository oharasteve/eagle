// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class CMD_Awk_Statement extends TokenSequence
{
	public CMD_Keyword AWK = new CMD_Keyword("awk");
	public @OPT TokenList<CMD_Awk_Option> opts;
	public @OPT TokenList<CMD_Argument> arg;
	
	public static class CMD_Awk_Option extends TokenChooser
	{
		public static class CMD_Awk_Option_F extends TokenSequence
		{
			public CMD_Punctuation minus = new CMD_Punctuation('-');
			public CMD_Keyword F = new CMD_Keyword("f");
			public CMD_Argument arg;
		}

		public static class CMD_Awk_Option_V extends TokenSequence
		{
			public CMD_Punctuation minus = new CMD_Punctuation('-');
			public CMD_Keyword V = new CMD_Keyword("v");
			public CMD_Argument variableDefinitions;
		}
	}
}
