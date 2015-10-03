// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Filename;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenSequence;

public class SQL_AtAtStatement extends TokenSequence
{
	public SQL_Punctuation atat = new SQL_Punctuation("@@");
	public SQL_Filename filename;
	public @OPT SQL_Punctuation semicolon = new SQL_Punctuation(';');
}
