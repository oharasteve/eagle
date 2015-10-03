// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Java_WhileStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.12") Java_Keyword WHILE = new Java_Keyword("while");
	public Java_Punctuation leftParen = new Java_Punctuation('(');
	public @NOSPACE Java_Expression condition;
	public @NOSPACE Java_Punctuation rightParen = new Java_Punctuation(')');
	public @OPT Java_Comment comment;
	public Java_Statement whileStatement;
}
