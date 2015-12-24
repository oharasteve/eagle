// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Terminals;

import com.eagle.tokens.TerminalKeywordChoice;

public class Template_KeywordChoice extends TerminalKeywordChoice
{
	// Need default constructor for reading from the XML file
	public Template_KeywordChoice()
	{
		super();
	}
	
	public Template_KeywordChoice(String... words)
	{
		super(words);
	}
}
