// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_ReturnStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.17") Java_Keyword RETURN = new Java_Keyword("return");
	public @OPT Java_Expression expression;
	public @NOSPACE PunctuationSemicolon semicolon;
}
