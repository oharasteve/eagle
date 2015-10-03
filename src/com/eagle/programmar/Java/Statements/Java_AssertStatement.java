// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 11, 2014

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Java_AssertStatement extends TokenSequence
{
	public @NEWLINE Java_Keyword ASSERT = new Java_Keyword("assert");
	public Java_Expression condition;
	public @OPT Java_AssertMessage message;
	public @NOSPACE Java_Punctuation semicolon = new Java_Punctuation(';');
	
	public static class Java_AssertMessage extends TokenSequence
	{
		public Java_Punctuation colon = new Java_Punctuation(':');
		public Java_Expression message;
	}
}
