// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_BreakStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.15") Java_Keyword BREAK = new Java_Keyword("break");
	public @OPT Java_Identifier_Reference label;
	public @NOSPACE PunctuationSemicolon semicolon;
}
