// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Java_DoStatement extends TokenSequence
{
	public @OPT @NEWLINE Java_Label label;
	public @DOC("statements.html#14.13") Java_Keyword DO = new Java_Keyword("do");
	public @OPT Java_Comment comment;
	public Java_Statement doStatement;
	public @NEWLINE Java_Keyword WHILE = new Java_Keyword("while");
	public Java_Punctuation leftParen = new Java_Punctuation('(');
	public @NOSPACE Java_Expression condition;
	public @NOSPACE Java_Punctuation rightParen = new Java_Punctuation(')');
	public @NOSPACE Java_Punctuation semicolon = new Java_Punctuation(';');
}
