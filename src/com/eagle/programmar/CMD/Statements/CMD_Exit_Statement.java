// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMD_Exit_Statement extends TokenSequence
{
	public @DOC("exit.mspx") CMD_Keyword EXIT = new CMD_Keyword("exit");
	public @OPT TokenList<CMD_Del_Option> opts;
	public @OPT CMD_Number exitValue;
	
	public static class CMD_Del_Option extends TokenChooser
	{
		public static class CMD_Del_Option_B extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Keyword B = new CMD_Keyword("b");
		}
	}
}