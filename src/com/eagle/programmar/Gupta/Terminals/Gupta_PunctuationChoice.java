// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.Gupta.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Gupta_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Gupta_PunctuationChoice()
	{
		super();
	}
	
	public Gupta_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}