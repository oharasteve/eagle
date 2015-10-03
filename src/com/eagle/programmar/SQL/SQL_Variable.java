// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 9, 2014

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class SQL_Variable extends TokenSequence
{
	public SQL_Identifier_Reference field;
	public @OPT TokenList<SQL_MoreFields> more;
	
	public static class SQL_MoreFields extends TokenSequence
	{
		public SQL_Punctuation dot = new SQL_Punctuation('.');
		public SQL_Identifier_Reference field;
	}
}
