// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenSequence;

public class Natural_SuspendStatement extends TokenSequence
{
	public @DOC("sm/suspend.htm") Natural_Keyword SUSPEND = new Natural_Keyword("SUSPEND");
	public Natural_Keyword IDENTICAL = new Natural_Keyword("IDENTICAL");
	public Natural_Keyword SUPPRESS = new Natural_Keyword("SUPPRESS");
}
