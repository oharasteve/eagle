// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class SQL_DropStatement extends TokenSequence
{
	public SQL_Keyword DROP = new SQL_Keyword("DROP");
	public SQL_DropWhat what;
	public SQL_Punctuation semicolon = new SQL_Punctuation(';');

	public static class SQL_DropWhat extends TokenChooser
	{
		public static class SQL_DropFunction extends TokenSequence
		{
			public SQL_Keyword FUNCTION = new SQL_Keyword("FUNCTION");
			public SQL_Identifier_Reference func;
		}
		
		public static class SQL_DropPackage extends TokenSequence
		{
			public SQL_Keyword PACKAGE = new SQL_Keyword("PACKAGE");
			public SQL_Identifier_Reference pack;
		}
		
		public static class SQL_DropProcedure extends TokenSequence
		{
			public SQL_Keyword PROCEDURE = new SQL_Keyword("PROCEDURE");
			public SQL_Identifier_Reference proc;
		}
		
		public static class SQL_DropRole extends TokenSequence
		{
			public SQL_Keyword ROLE = new SQL_Keyword("ROLE");
			public SQL_Identifier_Reference role;
		}
		
		public static class SQL_DropSynonym extends TokenSequence
		{
			public SQL_Keyword PUBLIC = new SQL_Keyword("PUBLIC");
			public SQL_Keyword SYNONYM = new SQL_Keyword("SYNONYM");
			public SQL_Identifier_Reference synonym;
		}
		
		public static class SQL_DropTable extends TokenSequence
		{
			public SQL_Keyword TABLE = new SQL_Keyword("TABLE");
			public SQL_Identifier_Reference table;
		}
		
		public static class SQL_DropView extends TokenSequence
		{
			public SQL_Keyword VIEW = new SQL_Keyword("VIEW");
			public SQL_Identifier_Reference view;
		}
	}
}
