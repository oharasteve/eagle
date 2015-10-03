// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class Gupta_Literal extends TerminalLiteralToken
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
		int sc = _currentChar;
		if (ch == '\'')
		{
			int endChar = sc;
			while (true)
			{
				endChar++;
				if (endChar >= recLen)
				{
					// Continued onto the next line, ick.
					_txt += rec.substring(sc + 1);
					_endLine++;
					rec = lines.get(_endLine);
					recLen = rec.length();
					sc = -1;
					endChar = sc;
					continue;
				}
				ch = rec.charAt(endChar);
				if (ch == '\'') break;
			}
			foundIt(_endLine, endChar);
			_txt += rec.substring(sc + 1, endChar);
			return true;
		}
		return false;
	}
}
