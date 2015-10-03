// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class Natural_Tab extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch = rec.charAt(_currentChar);
		if (Character.isDigit(ch))
		{
			int endChar = _currentChar;
			while (true)
			{
				endChar++;
				if (endChar >= recLen) return false;
				ch = rec.charAt(endChar);
				if (ch == 'T' || ch == 'X') break;
				if (!Character.isDigit(ch)) return false;
			}
			foundIt(_currentLine, endChar);
			_txt = rec.substring(_currentChar, endChar + 1);
			return true;
		}
		return false;
	}
	
	@Override
	public String showString()
	{
		return "Tab";
	}

	@Override
	public String description()
	{
		return "A tab, such as 23T";
	}
}
