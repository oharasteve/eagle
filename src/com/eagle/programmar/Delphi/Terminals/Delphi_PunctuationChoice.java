// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.Delphi.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Delphi_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Delphi_PunctuationChoice()
	{
		super();
	}
	
	public Delphi_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}