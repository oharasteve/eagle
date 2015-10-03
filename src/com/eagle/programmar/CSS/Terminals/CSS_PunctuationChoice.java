// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.CSS.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class CSS_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public CSS_PunctuationChoice()
	{
		super();
	}
	
	public CSS_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}