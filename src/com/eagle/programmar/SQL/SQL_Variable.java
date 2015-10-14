// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 9, 2014

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class SQL_Variable extends TokenSequence
{
	public SeparatedList<SQL_Identifier_Reference,PunctuationPeriod> field;
}
