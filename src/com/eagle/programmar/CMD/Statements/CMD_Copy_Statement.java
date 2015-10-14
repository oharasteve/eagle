// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_Copy_Statement extends TokenSequence
{
	public @DOC("copy.mspx") CMD_Keyword COPY = new CMD_Keyword("copy");
	public @OPT TokenList<CMD_Copy_Option> opts;
	public CMD_Argument copyFrom;
	public @OPT CMD_Argument copyTo;
	
	public static class CMD_Copy_Option extends TokenChooser
	{
		public static class CMD_Copy_Option_Y extends TokenSequence
		{
			public PunctuationSlash slash;
			public CMD_Keyword Y = new CMD_Keyword("y");
		}
	}
}
