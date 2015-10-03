// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Javascript_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Javascript_PunctuationChoice()
	{
		super();
	}
	
	public Javascript_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}