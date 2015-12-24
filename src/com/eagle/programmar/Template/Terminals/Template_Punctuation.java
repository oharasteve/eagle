// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class Template_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public Template_Punctuation()
	{
		this('\0');
	}

	public Template_Punctuation(char punct)
	{
		super(punct);
	}

	public Template_Punctuation(String punct)
	{
		super(punct);
	}
}
