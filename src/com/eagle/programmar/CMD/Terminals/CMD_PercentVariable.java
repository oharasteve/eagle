// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2011

package com.eagle.programmar.CMD.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalIdentifierToken;

public class CMD_PercentVariable extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		int endChar = _currentChar + 3;
		if (endChar >= recLen) return false;
		if (rec.charAt(_currentChar) != '%') return false;
		if (rec.charAt(_currentChar+1) != '%') return false;
		// More stuff goes here, starting with ~
		if (!Character.isLetterOrDigit(rec.charAt(_currentChar+2))) return false;

		_id = rec.substring(_currentChar, endChar);
		foundIt(_currentLine, endChar - 1);
		return true;
	}
	
	@Override
	public String showString()
	{
		return "Percent Identifier";
	}

	@Override
	public String description()
	{
		return "An identifier starting with %%.";
	}
}
