// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class CMD_Goto_Statement extends TokenSequence
{
	public @DOC("goto.mspx") CMD_Keyword GOTO = new CMD_Keyword("goto");
	public @OPT PunctuationColon colon;
	public CMD_Goto_What gotoWhat;
	
	public static class CMD_Goto_What extends TokenChooser
	{
		public @CHOICE CMD_Identifier_Reference label;
		public @CHOICE CMD_Keyword EOF = new CMD_Keyword("eof");
	}
}
