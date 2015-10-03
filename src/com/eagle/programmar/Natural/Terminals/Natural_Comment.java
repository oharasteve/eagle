// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

package com.eagle.programmar.Natural.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class Natural_Comment extends TerminalCommentToken
{
	public Natural_Comment()
	{
		this("");
	}

	public Natural_Comment(String comment)
	{
		super(comment);
	}

	// Allowed patterns are star-eoln, star-space, star-star, slash-star
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(this._currentLine);
		int nc = rec.length();

		char firstChar = rec.charAt(_currentChar);
		if (firstChar == '*' || firstChar == '/')
		{
			char nextChar;
			if (_currentChar + 1 >= nc)
			{
				nextChar = ' ';
				_comment = "";
			}
			else
			{
				nextChar = rec.charAt(_currentChar + 1);
				_comment = rec.substring(_currentChar, nc);
			}
			
			if ((firstChar == '*' && nextChar == ' ') || nextChar == '*')
			{
				foundIt(_currentLine, nc);
				return true;
			}
		}
		return false;
	}
}
