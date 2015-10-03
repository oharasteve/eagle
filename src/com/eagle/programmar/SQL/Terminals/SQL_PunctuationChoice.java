// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.SQL.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class SQL_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public SQL_PunctuationChoice()
	{
		super();
	}
	
	public SQL_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}
