// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 9, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.tokens.TokenSequence;

public class CMD_Shift_Statement extends TokenSequence
{
	public CMD_Keyword SHIFT = new CMD_Keyword("shift");
	public @OPT CMD_Number shiftAmount;
}
