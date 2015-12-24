// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 16, 2013

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;

public abstract class TerminalEndOfLine extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) != FOUND.EOLN) return false;
		while (true)
		{
			_currentLine++;
			if (findStart(lines, _currentLine, 0) != FOUND.EOLN) break;
		}
		foundIt(_currentLine, -1);
		return true;
	}
	
	@Override
	public String showString()
	{
		return "EOLN";
	}

	@Override
	public String description()
	{
		return "End of line";
	}
}
