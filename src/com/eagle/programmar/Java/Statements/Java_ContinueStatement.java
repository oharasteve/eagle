// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_ContinueStatement extends TokenSequence
{
	public @DOC("statements.html#14.16") Java_Keyword CONTINUE = new Java_Keyword("continue");
	public @OPT Java_Identifier_Reference label;
	public PunctuationSemicolon semicolon;
}
