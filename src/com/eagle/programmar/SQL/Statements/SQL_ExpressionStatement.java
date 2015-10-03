// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenSequence;

public class SQL_ExpressionStatement extends TokenSequence
{
	public SQL_Expression expr;
	public SQL_Punctuation semicolon = new SQL_Punctuation(';');
}
