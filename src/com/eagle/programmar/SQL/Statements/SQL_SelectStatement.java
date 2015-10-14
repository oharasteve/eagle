// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Table_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_SelectStatement extends TokenSequence
{
	public @DOC("sql_select.asp") SQL_Keyword SELECT = new SQL_Keyword("SELECT");
	public SeparatedList<SQL_SelectWhat,PunctuationComma> what;
	public @OPT SQL_SelectInto into;
	public SQL_SelectFrom from;
	public @OPT SQL_SelectWhere where;
	public @OPT SQL_SelectReadOnly readOnly;
	public PunctuationSemicolon semicolon;
	
	public static class SQL_SelectWhat extends TokenChooser
	{
		public @LAST SQL_Expression expr;
		
		public static class SQL_SelectExpression extends TokenSequence
		{
			public SQL_Expression what;
			public SQL_Identifier_Reference name;
		}
	}
	
	public static class SQL_SelectInto extends TokenSequence
	{
		public SQL_Keyword INTO = new SQL_Keyword("INTO");
		public SQL_Identifier_Reference table;
	}
	
	public static class SQL_SelectFrom extends TokenSequence
	{
		public SQL_Keyword FROM = new SQL_Keyword("FROM");
		public SQL_Identifier_Reference table;
		public @OPT SQL_Table_Definition tableName;
		public @OPT TokenList<SQL_SelectFromMore> more;
		
		public static class SQL_SelectFromMore extends TokenSequence
		{
			public PunctuationComma comma;
			public SQL_Identifier_Reference table;
			public @OPT SQL_Table_Definition tableName;
		}
	}
	
	public static class SQL_SelectWhere extends TokenSequence
	{
		public SQL_Keyword WHERE = new SQL_Keyword("WHERE");
		public SQL_Expression condition;
	}
	
	public static class SQL_SelectReadOnly extends TokenSequence
	{
		public SQL_Keyword WITH = new SQL_Keyword("WITH");
		public SQL_Keyword READ = new SQL_Keyword("READ");
		public SQL_Keyword ONLY = new SQL_Keyword("ONLY");
	}
}
