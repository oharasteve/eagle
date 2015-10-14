// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2015

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_PragmaStatement extends TokenSequence
{
	public SQL_Keyword PRAGMA = new SQL_Keyword("PRAGMA");
	public TokenList<SQL_PragmaClause> clauses;
	public PunctuationSemicolon semicolon;
	
	public static class SQL_PragmaClause extends TokenChooser
	{
		public static class SQL_Pragma_ForeignKeys extends TokenSequence
		{
			public SQL_Keyword FOREIGN_KEYS = new SQL_Keyword("FOREIGN_KEYS");
			public PunctuationEquals equals;
			public SQL_Keyword OFF = new SQL_Keyword("OFF");
		}
	}
}
