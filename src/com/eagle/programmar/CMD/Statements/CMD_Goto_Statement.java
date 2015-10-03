// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CMD_Goto_Statement extends TokenSequence
{
	public @DOC("goto.mspx") CMD_Keyword GOTO = new CMD_Keyword("goto");
	public @OPT CMD_Punctuation colon = new CMD_Punctuation(':');
	public CMD_Goto_What gotoWhat;
	
	public static class CMD_Goto_What extends TokenChooser
	{
		public CMD_Identifier_Reference label;
		public CMD_Keyword EOF = new CMD_Keyword("eof");
	}
}
