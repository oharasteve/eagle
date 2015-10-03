// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.tokens.TokenSequence;

public class Natural_ReleaseStatement extends TokenSequence
{
	public @DOC("sm/release.htm") Natural_Keyword RELEASE = new Natural_Keyword("RELEASE");
	public Natural_Keyword SET = new Natural_Keyword("SET");
	public Natural_Literal literal;
}
