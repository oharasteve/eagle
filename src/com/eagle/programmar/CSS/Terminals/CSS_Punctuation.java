// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.CSS.Terminals;

import com.eagle.tokens.TerminalPunctuationToken;

public class CSS_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public CSS_Punctuation()
	{
		this('\0');
	}

	public CSS_Punctuation(char punct)
	{
		super(punct);
	}

	public CSS_Punctuation(String punct)
	{
		super(punct);
	}
}
