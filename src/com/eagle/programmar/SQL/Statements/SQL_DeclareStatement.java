// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 6, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Type;
import com.eagle.programmar.SQL.Symbols.SQL_Declare_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class SQL_DeclareStatement extends TokenSequence
{
	public SQL_Keyword DECLARE = new SQL_Keyword("DECLARE");
	public TokenList<SQL_Declaration> declarations;
	
	public static class SQL_Declaration extends TokenSequence
	{
		public SQL_Declare_Definition definition;
		public SQL_Type type;
		public SQL_Punctuation colonEquals = new SQL_Punctuation(":=");
		public SQL_Expression value;
		public SQL_Punctuation semicolon = new SQL_Punctuation(';');
	}
}
