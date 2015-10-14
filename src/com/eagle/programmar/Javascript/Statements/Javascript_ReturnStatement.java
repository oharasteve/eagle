// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_ReturnStatement extends TokenSequence
{
	public @NEWLINE @DOC("js_functions.asp") Javascript_Keyword RETURN = new Javascript_Keyword("return");
	public @OPT Javascript_Expression expression;
	public @NOSPACE @OPT PunctuationSemicolon semicolon;
}
