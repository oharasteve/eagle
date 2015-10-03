// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 27, 2014

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;

public abstract class TerminalEndOfFile extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) != FOUND.EOF) return false;
		_currentLine = lines.getCurrentLine();
		_currentChar = lines.getCurrentChar();
		foundIt(_currentLine, _currentChar);
		return true;
	}
	
	@Override
	public String showString()
	{
		return "EOF";
	}

	@Override
	public String description()
	{
		return "End of file";
	}
}
