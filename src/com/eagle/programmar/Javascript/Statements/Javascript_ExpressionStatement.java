// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Javascript_ExpressionStatement extends TokenSequence
{
	public @NEWLINE Javascript_Expression expr;
	public @NOSPACE Javascript_Punctuation semicolon = new Javascript_Punctuation(';');
}
