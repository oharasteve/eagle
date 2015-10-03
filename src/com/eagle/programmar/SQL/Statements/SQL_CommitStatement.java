// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenSequence;

public class SQL_CommitStatement extends TokenSequence
{
	public SQL_Keyword COMMIT = new SQL_Keyword("COMMIT");
	public SQL_Punctuation semicolon = new SQL_Punctuation(';');
}
