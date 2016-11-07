// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 16, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_CD_Statement extends TokenSequence
{
	public @DOC("chdir.mspx") CMD_Keyword CD = new CMD_Keyword("cd");
	public @OPT TokenList<CMD_CD_Option> opts;
	public CMD_Argument dir;
	
	public static class CMD_CD_Option extends TokenChooser
	{
		public @CHOICE static class CMD_CD_Option_D extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword D = new CMD_Keyword("d");
		}
	}
}
