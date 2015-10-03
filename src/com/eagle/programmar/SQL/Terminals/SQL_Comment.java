// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class SQL_Comment extends TerminalCommentToken
{
	public SQL_Comment()
	{
		this("");
	}
	
	public SQL_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		char ch = rec.charAt(_currentChar);
		if (ch == '#')
		{
			foundIt(_currentLine, nc);
			_comment = rec.substring(_currentChar, nc);
			return true;
		}

		if (ch == '-')
		{
			return super.possibleCommentToEndOfLine(rec, "--");
		}
		
		if (ch == '/' && _currentChar + 1 < nc && rec.charAt(_currentChar+1) == '*')
		{
			return super.possibleCommentPair2(lines, rec, "/*", "*/");
		}

		if (_currentChar + 2 < nc &&
				Character.toUpperCase(ch) == 'R' &&
				Character.toUpperCase(rec.charAt(_currentChar+1)) == 'E' &&
				Character.toUpperCase(rec.charAt(_currentChar+2)) == 'M')
		{
			foundIt(_currentLine, nc);
			_comment = rec.substring(_currentChar, nc);
			return true;
		}

		return false;
	}
}
