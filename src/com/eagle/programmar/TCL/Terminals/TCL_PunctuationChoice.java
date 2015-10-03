// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.TCL.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class TCL_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public TCL_PunctuationChoice()
	{
		super();
	}
	
	public TCL_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}