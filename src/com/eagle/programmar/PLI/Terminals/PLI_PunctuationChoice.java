// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.PLI.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class PLI_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public PLI_PunctuationChoice()
	{
		super();
	}
	
	public PLI_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}
