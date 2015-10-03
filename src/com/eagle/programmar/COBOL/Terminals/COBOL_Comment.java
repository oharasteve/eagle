// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.EagleSyntax;
import com.eagle.tokens.TerminalCommentToken;

public class COBOL_Comment extends TerminalCommentToken
{
	public COBOL_Comment()
	{
		this("");
	}

	public COBOL_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleSyntax syntax = getSyntax();
		EagleLineReader rec = lines.get(_currentLine);
		if (_currentChar != syntax._commentColumn)
		{
			if (_currentChar != syntax._commentColumn + 1)
			{
				return false;	// The '*' must be in column 1 for free format, 7 or 8 for fixed
			}
		}
		char ch = rec.charAt(_currentChar);
		if (ch != '*' && ch != '/') return false;
		
		_endChar = syntax.recLen(lines, _currentLine);
		foundIt(_currentLine, _endChar);
		_comment = rec.substring(_currentChar, _endChar);
		return true;
	}
}
