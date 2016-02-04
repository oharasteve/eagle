// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;

public abstract class TokenRestOfLine extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		_currentLine = lines.getCurrentLine();
		_currentChar = lines.getCurrentChar();
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		while (_currentChar < recLen && rec.charAt(_currentChar) == ' ') _currentChar++;
		foundIt(_currentLine, recLen);
		_txt = rec.substring(_currentChar, recLen);
		return true;
	}

	@Override
	public String description()
	{
		return "Rest of the line";
	}
}
