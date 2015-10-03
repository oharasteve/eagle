// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class SQL_UpdateStatement extends TokenSequence
{
	public @DOC("sql_update.asp") SQL_Keyword UPDATE = new SQL_Keyword("UPDATE");
	public SQL_Identifier_Reference table;
	public SQL_Keyword SET = new SQL_Keyword("SET");
	public SQL_UpdateAssignment assignment;
	public @OPT TokenList<SQL_UpdateMoreAssignments> more;
	public SQL_Keyword WHERE = new SQL_Keyword("WHERE");
	public SQL_Expression condition;
	public SQL_Punctuation semicolon = new SQL_Punctuation(';');

	public static class SQL_UpdateAssignment extends TokenSequence
	{
		public SQL_Identifier_Reference var;
		public SQL_Punctuation equals = new SQL_Punctuation('=');
		public SQL_Expression value;
	}
	
	public static class SQL_UpdateMoreAssignments extends TokenSequence
	{
		public SQL_Punctuation comma = new SQL_Punctuation(',');
		public SQL_UpdateAssignment assignment;
	}
}
