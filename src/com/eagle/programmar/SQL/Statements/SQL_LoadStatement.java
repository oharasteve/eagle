// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.tokens.TokenSequence;

public class SQL_LoadStatement extends TokenSequence
{
	public SQL_Keyword LOAD = new SQL_Keyword("LOAD");
	public SQL_Keyword DATA = new SQL_Keyword("DATA");
	public SQL_Keyword LOCAL = new SQL_Keyword("LOCAL");
	public SQL_Keyword INFILE = new SQL_Keyword("INFILE");
	public SQL_Literal inFile;
	public SQL_Keyword INTO = new SQL_Keyword("INTO");
	public SQL_Keyword TABLE = new SQL_Keyword("TABLE");
	public SQL_Identifier_Reference table;
	public SQL_Keyword FIELDS = new SQL_Keyword("FIELDS");
	public SQL_Keyword TERMINATED1 = new SQL_Keyword("TERMINATED");
	public SQL_Keyword BY1 = new SQL_Keyword("BY");
	public SQL_Literal fieldTerminator;
	public SQL_Keyword ENCLOSED = new SQL_Keyword("ENCLOSED");
	public SQL_Keyword BY2 = new SQL_Keyword("BY");
	public SQL_Literal enclosure;
	public SQL_Keyword ESCAPED = new SQL_Keyword("ESCAPED");
	public SQL_Keyword BY3 = new SQL_Keyword("BY");
	public SQL_Literal escaped;
	public SQL_Keyword LINES = new SQL_Keyword("LINES");
	public SQL_Keyword TERMINATED2 = new SQL_Keyword("TERMINATED");
	public SQL_Keyword BY4 = new SQL_Keyword("BY");
	public SQL_Literal lineTerminator;
}

