// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.VB.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class VB_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public VB_PunctuationChoice()
	{
		super();
	}
	
	public VB_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}