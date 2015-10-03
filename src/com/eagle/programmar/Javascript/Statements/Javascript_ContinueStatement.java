// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Javascript_ContinueStatement extends TokenSequence
{
	public Javascript_Keyword CONTINUE = new Javascript_Keyword("continue");
	public Javascript_Punctuation semicolon = new Javascript_Punctuation(';');
}
