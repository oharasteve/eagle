// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.HTML.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class HTML_CData extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar + 8 >= recLen) return false;
		
		if (rec.charAt(_currentChar) == '<' &&
				rec.charAt(_currentChar + 1) == '!' &&
				rec.charAt(_currentChar + 2) == '[' &&
				rec.charAt(_currentChar + 3) == 'C' &&
				rec.charAt(_currentChar + 4) == 'D' &&
				rec.charAt(_currentChar + 5) == 'A' &&
				rec.charAt(_currentChar + 6) == 'T' &&
				rec.charAt(_currentChar + 7) == 'A' &&
				rec.charAt(_currentChar + 8) == '[')
		{
			_endLine = _currentLine;
			_txt = "";
			int endChar = _currentChar + 8;
			while (true)
			{
				endChar++;
				if (endChar + 2 >= recLen)
				{
					// Continued onto the next line, ick.
					_txt += rec.substring(_currentChar < 0 ? 0 : _currentChar) + '\n';
					_endLine++;
					rec = lines.get(_endLine);
					recLen = rec.length();
					_currentChar = -1;
					endChar = _currentChar;
					continue;
				}
				
				if (rec.charAt(endChar) == ']' &&
						rec.charAt(endChar + 1) == ']' &&
						rec.charAt(endChar + 2) == '>')
				{
					foundIt(_endLine, endChar + 2);
					_txt += rec.substring(_currentChar < 0 ? 0 : _currentChar, endChar + 3);
					return true;
				}
			}
		}
		return false;
	}
}
