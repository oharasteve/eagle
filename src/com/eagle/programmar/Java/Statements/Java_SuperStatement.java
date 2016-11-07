// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_SuperStatement extends TokenSequence
{
	public Java_Keyword SUPER = new Java_Keyword("super");
	public @NOSPACE PunctuationLeftParen leftParen;
	public @OPT @NOSPACE Java_ArgumentList args;
	public @NOSPACE PunctuationRightParen rightParen;
	public @NOSPACE PunctuationSemicolon semicolon;
}
