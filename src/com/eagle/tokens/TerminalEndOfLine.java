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
		foundIt(_currentLine+1, -1);
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
