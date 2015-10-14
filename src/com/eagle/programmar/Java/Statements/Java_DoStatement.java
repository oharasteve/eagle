// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_DoStatement extends TokenSequence
{
	public @OPT @NEWLINE Java_Label label;
	public @DOC("statements.html#14.13") Java_Keyword DO = new Java_Keyword("do");
	public @OPT Java_Comment comment;
	public Java_Statement doStatement;
	public @NEWLINE Java_Keyword WHILE = new Java_Keyword("while");
	public PunctuationLeftParen leftParen;
	public @NOSPACE Java_Expression condition;
	public @NOSPACE PunctuationRightParen rightParen;
	public @NOSPACE PunctuationSemicolon semicolon;
}
