// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 6, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.Symbols.SQL_Declare_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class SQL_ForStatement extends TokenSequence
{
	public SQL_Keyword FOR = new SQL_Keyword("FOR");
	public SQL_Declare_Definition variable;
	public SQL_Keyword IN = new SQL_Keyword("IN");
	public SQL_Expression start;
	public SQL_Punctuation dotDot = new SQL_Punctuation("..");
	public SQL_Expression finish;
	public SQL_Keyword LOOP1 = new SQL_Keyword("LOOP");
	
	public TokenList<SQL_StatementOrComment> statements;

	public SQL_Keyword END = new SQL_Keyword("END");
	public SQL_Keyword LOOP2 = new SQL_Keyword("LOOP");
	public SQL_Punctuation semicolon = new SQL_Punctuation(';');
}
