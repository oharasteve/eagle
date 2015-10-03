// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

package com.eagle.programmar.Natural.Terminals;

import com.eagle.tokens.TerminalKeywordToken;

public class Natural_Keyword extends TerminalKeywordToken
{
	// Need default constructor for reading from the XML file
	public Natural_Keyword()
	{
		this("");
	}

	public Natural_Keyword(String word)
	{
		super(word);

		// Dang, didn't work. No instances are created in time ... 
		// Add it to the global list of reserved keywords
		// if (!Natural_Program.keywords.contains(word)) Natural_Program.keywords.put(word, word);
	}
}
