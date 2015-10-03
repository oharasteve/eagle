// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.Python.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Python_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Python_PunctuationChoice()
	{
		super();
	}
	
	public Python_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}