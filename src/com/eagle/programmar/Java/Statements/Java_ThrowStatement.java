// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_ThrowStatement extends TokenSequence
{
	public @DOC("statements.html#14.18") Java_Keyword THROW = new Java_Keyword("throw");
	public Java_Expression expression;
	public PunctuationSemicolon semicolon;
}
