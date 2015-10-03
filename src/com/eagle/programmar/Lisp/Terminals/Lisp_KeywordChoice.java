// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

package com.eagle.programmar.Lisp.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Lisp_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Lisp_KeywordChoice()
	{
		super();
	}
	
	public Lisp_KeywordChoice(String... words)
	{
		super(words);
	}
}
