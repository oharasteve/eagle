// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression.Java_ArgumentList;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Java_SuperStatement extends TokenSequence
{
	public Java_Keyword SUPER = new Java_Keyword("super");
	public Java_Punctuation leftParen = new Java_Punctuation('(');
	public @OPT Java_ArgumentList args;
	public Java_Punctuation rightParen = new Java_Punctuation(')');
	public Java_Punctuation semicolon = new Java_Punctuation(';');
}
