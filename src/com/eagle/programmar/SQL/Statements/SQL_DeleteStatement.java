// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenSequence;

public class SQL_DeleteStatement extends TokenSequence
{
	public @DOC("sql_delete.asp") SQL_Keyword DELETE = new SQL_Keyword("DELETE");
	public SQL_Keyword FROM = new SQL_Keyword("FROM");
	public SQL_Identifier_Reference table;
	public SQL_Keyword WHERE = new SQL_Keyword("WHERE");
	public SQL_Expression condition;
	public SQL_Punctuation semicolon = new SQL_Punctuation(';');
}
