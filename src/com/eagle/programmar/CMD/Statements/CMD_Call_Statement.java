// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class CMD_Call_Statement extends TokenSequence
{
	public @DOC("call.mspx") CMD_Keyword CALL = new CMD_Keyword("call");
	public @OPT CMD_Punctuation colon = new CMD_Punctuation(':');
	public CMD_Argument what;
	public @OPT TokenList<CMD_Call_Parameter> args;

	public static class CMD_Call_Parameter extends TokenChooser
	{
		public CMD_Argument arg;
		
		public static class CMD_Call_Minus_Option extends TokenSequence
		{
			public CMD_Punctuation minus = new CMD_Punctuation('-');
			public CMD_Argument option;
		}
		
		public static class CMD_Call_Slash_Option extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Argument option;
		}
	}
}
