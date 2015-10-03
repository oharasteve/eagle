// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class SQL_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public SQL_Punctuation()
	{
		this('\0');
	}

	public SQL_Punctuation(char punct)
	{
		super(punct);
	}

	public SQL_Punctuation(String punct)
	{
		super(punct);
	}
}
