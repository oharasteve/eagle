// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Label;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_UpdateStatement extends TokenSequence
{
	public @DOC("sm/update.htm") Natural_Keyword UPDATE = new Natural_Keyword("UPDATE");
	public PunctuationLeftParen leftParen;
	public Natural_Label label;
	public PunctuationRightParen rightParen;
}
