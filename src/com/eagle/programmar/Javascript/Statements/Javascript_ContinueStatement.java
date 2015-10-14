// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_ContinueStatement extends TokenSequence
{
	public Javascript_Keyword CONTINUE = new Javascript_Keyword("continue");
	public PunctuationSemicolon semicolon;
}
