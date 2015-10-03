// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Java_SynchronizedStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.19") Java_Keyword SYNCHRONIZED = new Java_Keyword("synchronized");
	public Java_Punctuation leftParen = new Java_Punctuation('(');
	public @NOSPACE Java_Expression expr;
	public @NOSPACE Java_Punctuation rightParen = new Java_Punctuation(')');
	public Java_Statement syncStatement;
}
