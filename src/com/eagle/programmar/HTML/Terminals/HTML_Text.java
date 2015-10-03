// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class HTML_Text extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch = rec.charAt(_currentChar);
		_endLine = _currentLine;
		_txt = "";
		if (ch == '<') return false;

		if (ch == '{' && _currentChar + 1 < recLen)
		{
			char ch2 = rec.charAt(_currentChar + 1);
			if (ch2 == '{' || ch2 == '%') return false;
		}
		
		int endChar = _currentChar;
		while (true)
		{
			endChar++;
			if (endChar >= recLen) break;
			ch = rec.charAt(endChar);
			if (ch == '<') break;
			if (ch == '{' && endChar + 1 < recLen)
			{
				char ch2 = rec.charAt(endChar + 1);
				if (ch2 == '{' || ch2 == '%') break;
			}
		}
		foundIt(_endLine, endChar-1);
		_txt += rec.substring(_currentChar, endChar);
		return true;
	}
}
