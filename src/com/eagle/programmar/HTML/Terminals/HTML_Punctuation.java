// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class HTML_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public HTML_Punctuation()
	{
		this('\0');
	}

	public HTML_Punctuation(char punct)
	{
		super(punct);
	}

	public HTML_Punctuation(String punct)
	{
		super(punct);
	}
}
