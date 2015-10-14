// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Terminals.SQL_Identifier;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class SQL_Filename extends TokenSequence
{
	public SQL_Identifier file;
	public PunctuationPeriod dot;
	public SQL_Identifier ext;
}
