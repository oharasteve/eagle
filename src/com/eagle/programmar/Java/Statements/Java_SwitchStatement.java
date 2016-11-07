// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Statement.Java_StatementBlock.Java_StatementOrComment;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_SwitchStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.11") Java_Keyword SWITCH = new Java_Keyword("switch");
	public PunctuationLeftParen leftParen;
	public @NOSPACE Java_Expression val;
	public @NOSPACE PunctuationRightParen rightParen;
	public @INDENT PunctuationLeftBrace leftBrace;
	public TokenList<Java_SwitchClause> clause;
	public @OUTDENT PunctuationRightBrace rightBrace;
	
	public static class Java_SwitchClause extends TokenChooser
	{
		public @CHOICE Java_Comment comment;
		
		public @CHOICE static class Java_CaseClause extends TokenSequence
		{
			public @NEWLINE Java_Keyword CASE = new Java_Keyword("case");
			public Java_Expression expr;
			public @NOSPACE PunctuationColon colon;
			public @OPT TokenList<Java_StatementOrComment> statements;
		}
		
		public @CHOICE static class Java_DefaultClause extends TokenSequence
		{
			public @NEWLINE Java_Keyword DEFAULT = new Java_Keyword("default");
			public PunctuationColon colon;
			public @OPT TokenList<Java_StatementOrComment> statements;
		}
	}
}
