// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2015

package com.eagle.programmar.Lisp.Terminals;

import com.eagle.tokens.TerminalPunctuationChoice;

public class Lisp_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public Lisp_PunctuationChoice()
	{
		super();
	}
	
	public Lisp_PunctuationChoice(String... args)
	{
		super(args);
	}
}
