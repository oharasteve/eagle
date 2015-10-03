// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2011

package com.eagle.programmar.IBMASM.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class IBMASM_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public IBMASM_Comment()
	{
		this("");
	}
	
	public IBMASM_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (nc < 1) return false;
		
		/* Is it a comment? Star in column 1 means yes */
		if (rec.charAt(0) == '*')
		{
			_comment = rec.substring(0, nc);
			foundIt(_currentLine, nc);
			return true;
		}

		return false;
	}
}
