// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.HTML.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class HTML_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public HTML_PunctuationChoice()
	{
		super();
	}
	
	public HTML_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}
