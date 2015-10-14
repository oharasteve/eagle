// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Type;
import com.eagle.programmar.SQL.SQL_Variable;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_VariableStatement extends TokenSequence
{
	public SQL_Keyword VARIABLE = new SQL_Keyword("VARIABLE");
	public SQL_Variable var;
	public SQL_Type type;
	public PunctuationSemicolon semicolon;
}
