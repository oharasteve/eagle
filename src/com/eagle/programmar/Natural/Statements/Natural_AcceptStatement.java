// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Condition;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenSequence;

public class Natural_AcceptStatement extends TokenSequence
{
	public @DOC("sm/accept.htm") Natural_Keyword ACCEPT = new Natural_Keyword("ACCEPT");
	public Natural_Keyword IF = new Natural_Keyword("IF");
	public Natural_Condition cond;
}
