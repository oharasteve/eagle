// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Terminals.SQL_Identifier;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenSequence;

public class SQL_Filename extends TokenSequence
{
	public SQL_Identifier file;
	public SQL_Punctuation dot = new SQL_Punctuation('.');
	public SQL_Identifier ext;
}
