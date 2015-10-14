// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_RestOfLine;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class CMD_Echo_Statement extends TokenSequence
{
	public @DOC("echo.mspx") CMD_Keyword ECHO = new CMD_Keyword("echo");
	public @OPT PunctuationPeriod dot;
	public CMD_RestOfLine line;
}
