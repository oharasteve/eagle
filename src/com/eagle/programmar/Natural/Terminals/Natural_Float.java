// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalNumberToken;

public class Natural_Float extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch = rec.charAt(_currentChar);
		if (Character.isDigit(ch) ||
				(ch == '-' && _currentChar+1 < recLen && Character.isDigit(rec.charAt(_currentChar+1))) ||
				(ch == '.' && _currentChar+1 < recLen && Character.isDigit(rec.charAt(_currentChar+1))))
		{
			boolean foundDot = (ch == '.');
			int endChar = _currentChar;
			while (true)
			{
				endChar++;
				if (endChar >= recLen) break;
				ch = rec.charAt(endChar);
				if (ch == '.' && !foundDot)
				{
					// Only allow one decimal point
					foundDot = true;
					continue;
				}
				if (!Character.isDigit(ch)) break;
			}
			foundIt(_currentLine, endChar - 1);
			_numberAsText = rec.substring(_currentChar, endChar);
			return true;
		}
		return false;
	}
	
	@Override
	public String showString()
	{
		return "Float";
	}

	@Override
	public String description()
	{
		return "An floating point number, such as 23.5";
	}
}
