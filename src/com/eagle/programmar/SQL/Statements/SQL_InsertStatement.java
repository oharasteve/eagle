// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class SQL_InsertStatement extends TokenSequence
{
	public @DOC("sql_insert.asp") SQL_Keyword INSERT = new SQL_Keyword("INSERT");
	public SQL_Keyword INTO = new SQL_Keyword("INTO");
	public SQL_Identifier_Reference table;
	public SQL_InsertClause clause;
	public SQL_Punctuation semicolon = new SQL_Punctuation(';');
	
	public static class SQL_InsertClause extends TokenChooser
	{
		public static class SQL_InsertSet extends TokenSequence
		{
			public SQL_Keyword SET = new SQL_Keyword("SET");
			public SQL_InsertAssignment assignment;
			public @OPT TokenList<SQL_InsertMoreAssignments> more;

			public static class SQL_InsertAssignment extends TokenSequence
			{
				public SQL_Identifier_Reference var;
				public SQL_Punctuation equals = new SQL_Punctuation('=');
				public SQL_Expression value;
			}
			
			public static class SQL_InsertMoreAssignments extends TokenSequence
			{
				public SQL_Punctuation comma = new SQL_Punctuation(',');
				public SQL_InsertAssignment assignment;
			}
		}
		
		public static class SQL_InsertValues extends TokenSequence
		{
			public @OPT SQL_InsertNames insertNames;
			public SQL_Keyword VALUES = new SQL_Keyword("VALUES");
			public SQL_Punctuation leftParen = new SQL_Punctuation('(');
			public SQL_Expression value;
			public @OPT TokenList<SQL_InsertMoreValues> more;
			public SQL_Punctuation rightParen = new SQL_Punctuation(')');

			public static class SQL_InsertMoreValues extends TokenSequence
			{
				public SQL_Punctuation comma = new SQL_Punctuation(',');
				public SQL_Expression value;
			}
			
			public static class SQL_InsertNames extends TokenSequence
			{
				public SQL_Punctuation leftParen = new SQL_Punctuation('(');
				public SQL_Identifier_Reference var;
				public @OPT TokenList<SQL_InsertMoreNames> more;
				public SQL_Punctuation rightParen = new SQL_Punctuation(')');
				
				public static class SQL_InsertMoreNames extends TokenSequence
				{
					public SQL_Punctuation comma = new SQL_Punctuation(',');
					public SQL_Identifier_Reference var;
				}
			}
		}
	}
}
