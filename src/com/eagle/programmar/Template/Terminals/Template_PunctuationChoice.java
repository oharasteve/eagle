// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Template_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Template_PunctuationChoice()
	{
		super();
	}
	
	public Template_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
}