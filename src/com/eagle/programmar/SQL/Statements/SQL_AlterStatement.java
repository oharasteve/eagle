// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Constraint;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class SQL_AlterStatement extends TokenSequence
{
	public SQL_Keyword ALTER = new SQL_Keyword("ALTER");
	public SQL_AlterWhat what;
	public SQL_Punctuation semicolon = new SQL_Punctuation(';');
	
	public static class SQL_AlterWhat extends TokenChooser
	{
		public static class SQL_AlterTable extends TokenSequence
		{
			public SQL_Keyword TABLE = new SQL_Keyword("TABLE");
			public SQL_Identifier_Reference table;
			public SQL_KeywordChoice ADD = new SQL_KeywordChoice("ADD");
			public SQL_Constraint constraint;
			public @OPT SQL_OnDelete onDelete;
			
			public static class SQL_OnDelete extends TokenSequence
			{
				public SQL_Keyword ON = new SQL_Keyword("ON");
				public SQL_Keyword DELETE = new SQL_Keyword("DELETE");
				public SQL_Keyword CASCADE = new SQL_Keyword("CASCADE");
			}
		}
	}
}
