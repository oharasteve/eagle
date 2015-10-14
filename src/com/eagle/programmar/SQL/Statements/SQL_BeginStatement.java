// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_BeginStatement extends TokenSequence
{
	public SQL_Keyword BEGIN = new SQL_Keyword("BEGIN");
	public SQL_BeginWhat what;
	public PunctuationSemicolon semicolon;
	
	public static class SQL_BeginWhat extends TokenChooser
	{
		public static class SQL_BeginEnd extends TokenSequence
		{
			public TokenList<SQL_StatementOrComment> statements;
			public @OPT SQL_CommitStatement commit;
			public SQL_Keyword END = new SQL_Keyword("END");
		}
		
		public @FIRST static class SQL_BeginTransaction extends TokenSequence
		{
			public SQL_Keyword TRANSACTION = new SQL_Keyword("TRANSACTION");
			public PunctuationSemicolon semicolon;
			public TokenList<SQL_StatementOrComment> statements;
			public SQL_Keyword COMMIT = new SQL_Keyword("COMMIT");
		}
	}
}
