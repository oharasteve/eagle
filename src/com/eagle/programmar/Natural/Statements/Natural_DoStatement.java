// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 15, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_DoStatement extends TokenSequence
{
	public @DOC("sm/dodoend.htm") Natural_Keyword DO = new Natural_Keyword("DO");
	public @OPT TokenList<Natural_Statement> statements;
	public Natural_Keyword DOEND = new Natural_Keyword("DOEND");
}
