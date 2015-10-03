// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Java_IfStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.9") Java_Keyword IF = new Java_Keyword("if");
	public Java_Punctuation leftParen = new Java_Punctuation('(');
	public @NOSPACE Java_Expression condition;
	public @OPT TokenList<Java_Comment> comment1;
	public @NOSPACE Java_Punctuation rightParen = new Java_Punctuation(')');
	public @OPT TokenList<Java_Comment> comment2;
	public Java_Statement thenStatement;
	public @OPT Java_IfElseClause elseClause;
	
	public static class Java_IfElseClause extends TokenSequence
	{
		public @OPT TokenList<Java_Comment> comment3;
		public @NEWLINE Java_Keyword ELSE = new Java_Keyword("else");
		public @OPT Java_Comment comment;
		public Java_Statement elseStatement;
	}
}
