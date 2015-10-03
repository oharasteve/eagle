// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Statement.Java_StatementBlock.Java_StatementOrComment;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Java_SwitchStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.11") Java_Keyword SWITCH = new Java_Keyword("switch");
	public Java_Punctuation leftParen = new Java_Punctuation('(');
	public @NOSPACE Java_Expression val;
	public @NOSPACE Java_Punctuation rightParen = new Java_Punctuation(')');
	public @INDENT Java_Punctuation leftBrace = new Java_Punctuation('{');
	public TokenList<Java_SwitchClause> clause;
	public @OUTDENT Java_Punctuation rightBrace = new Java_Punctuation('}');
	
	public static class Java_SwitchClause extends TokenChooser
	{
		public Java_Comment comment;
		
		public static class Java_CaseClause extends TokenSequence
		{
			public @NEWLINE Java_Keyword CASE = new Java_Keyword("case");
			public Java_Expression expr;
			public @NOSPACE Java_Punctuation colon = new Java_Punctuation(':');
			public @OPT TokenList<Java_StatementOrComment> statements;
		}
		
		public static class Java_DefaultClause extends TokenSequence
		{
			public @NEWLINE Java_Keyword DEFAULT = new Java_Keyword("default");
			public Java_Punctuation colon = new Java_Punctuation(':');
			public @OPT TokenList<Java_StatementOrComment> statements;
		}
	}
}
