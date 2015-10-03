// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenSequence;

public class Natural_SkipStatement extends TokenSequence
{
	public @DOC("sm/skip.htm") Natural_Keyword SKIP = new Natural_Keyword("SKIP");
	public Natural_Expression amount;
}
