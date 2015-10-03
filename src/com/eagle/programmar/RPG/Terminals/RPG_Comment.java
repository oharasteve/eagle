// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class RPG_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public RPG_Comment()
	{
		this("");
	}
	
	public RPG_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (6 >= nc) return false;	// Need room for 7 columns
		
		/* Is it a comment? Star in column 7 means yes */
		if (rec.charAt(6) == '*')
		{
			_comment = rec.substring(6, nc);
			foundIt(_currentLine, nc);
			return true;
		}
		return false;
	}
}
