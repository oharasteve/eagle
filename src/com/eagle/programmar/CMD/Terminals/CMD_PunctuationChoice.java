// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.CMD.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class CMD_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public CMD_PunctuationChoice()
	{
		super();
	}
	
	public CMD_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}
