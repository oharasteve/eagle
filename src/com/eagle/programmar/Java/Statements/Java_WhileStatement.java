// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_WhileStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.12") Java_Keyword WHILE = new Java_Keyword("while");
	public PunctuationLeftParen leftParen;
	public @NOSPACE Java_Expression condition;
	public @NOSPACE PunctuationRightParen rightParen;
	public @OPT Java_Comment comment;
	public Java_Statement whileStatement;
}
