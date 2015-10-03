// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 15, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class Natural_EscapeStatement extends TokenSequence
{
	public @DOC("sm/escape.htm") Natural_Keyword ESCAPE = new Natural_Keyword("ESCAPE");
	public Natural_KeywordChoice what = new Natural_KeywordChoice(
			"BOTTOM", "TOP");
}
