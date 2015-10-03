// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenSequence;

public class CMD_SetLocal_Statement extends TokenSequence
{
	public @DOC("setlocal.mspx") CMD_Keyword SETLOCAL = new CMD_Keyword("setlocal");
}
