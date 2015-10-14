// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_ThrowStatement extends TokenSequence
{
	public @DOC("js_throw.asp") Javascript_Keyword THROW = new Javascript_Keyword("throw");
	public Javascript_Expression expression;
	public @OPT PunctuationComma comma;
	public @OPT Javascript_Expression extraExpression;
	public @OPT PunctuationSemicolon semicolon;
}
