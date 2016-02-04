// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 22, 2016

package com.eagle.programmar.Delphi.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class Delphi_Character extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar+1 >= recLen) return false;
		char ch1 = rec.charAt(_currentChar);
		if (ch1 != '#') return false;
		char ch2 = rec.charAt(_currentChar+1);
		if (! Character.isDigit(ch2)) return false;
		
		int endChar = _currentChar + 1;
		while (true)
		{
			endChar++;
			if (endChar >= recLen) break;
			char ch = rec.charAt(endChar);
			if (! Character.isDigit(ch) && ch != '#') break;
		}
		foundIt(_currentLine, endChar - 1);
		_txt = rec.substring(_currentChar, endChar);
		return true;
	}
}
