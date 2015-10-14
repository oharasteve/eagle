// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_InsertStatement extends TokenSequence
{
	public @DOC("sql_insert.asp") SQL_Keyword INSERT = new SQL_Keyword("INSERT");
	public SQL_Keyword INTO = new SQL_Keyword("INTO");
	public SQL_Identifier_Reference table;
	public SQL_InsertClause clause;
	public PunctuationSemicolon semicolon;
	
	public static class SQL_InsertClause extends TokenChooser
	{
		public static class SQL_InsertSet extends TokenSequence
		{
			public SQL_Keyword SET = new SQL_Keyword("SET");
			public SeparatedList<SQL_InsertAssignment,PunctuationComma> assignments;

			public static class SQL_InsertAssignment extends TokenSequence
			{
				public SQL_Identifier_Reference var;
				public PunctuationEquals equals;
				public SQL_Expression value;
			}
		}
		
		public static class SQL_InsertValues extends TokenSequence
		{
			public @OPT SQL_InsertNames insertNames;
			public SQL_Keyword VALUES = new SQL_Keyword("VALUES");
			public PunctuationLeftParen leftParen;
			public SeparatedList<SQL_Expression,PunctuationComma> values;
			public PunctuationRightParen rightParen;
			
			public static class SQL_InsertNames extends TokenSequence
			{
				public PunctuationLeftParen leftParen;
				public SeparatedList<SQL_Identifier_Reference,PunctuationComma> vars;
				public PunctuationRightParen rightParen;
			}
		}
	}
}
