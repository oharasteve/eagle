// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Template_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Template_Keyword()
	{
		this("");
	}

	public Template_Keyword(String word)
	{
		super(word);
	}
}
