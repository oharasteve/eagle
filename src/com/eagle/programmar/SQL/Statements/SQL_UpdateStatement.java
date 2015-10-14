// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_UpdateStatement extends TokenSequence
{
	public @DOC("sql_update.asp") SQL_Keyword UPDATE = new SQL_Keyword("UPDATE");
	public SQL_Identifier_Reference table;
	public SQL_Keyword SET = new SQL_Keyword("SET");
	public SeparatedList<SQL_UpdateAssignment,PunctuationComma> assignments;
	public SQL_Keyword WHERE = new SQL_Keyword("WHERE");
	public SQL_Expression condition;
	public PunctuationSemicolon semicolon;

	public static class SQL_UpdateAssignment extends TokenSequence
	{
		public SQL_Identifier_Reference var;
		public PunctuationEquals equals;
		public SQL_Expression value;
	}
}
