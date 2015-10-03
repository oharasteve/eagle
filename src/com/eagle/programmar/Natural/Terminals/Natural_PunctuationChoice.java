// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.Natural.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Natural_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Natural_PunctuationChoice()
	{
		super();
	}
	
	public Natural_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}