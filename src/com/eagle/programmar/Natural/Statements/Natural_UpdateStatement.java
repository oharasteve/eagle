// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Label;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Natural_UpdateStatement extends TokenSequence
{
	public @DOC("sm/update.htm") Natural_Keyword UPDATE = new Natural_Keyword("UPDATE");
	public Natural_Punctuation leftParen = new Natural_Punctuation('(');
	public Natural_Label label;
	public Natural_Punctuation rightParen = new Natural_Punctuation(')');
}
