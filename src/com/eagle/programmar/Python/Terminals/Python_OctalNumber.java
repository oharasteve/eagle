// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 14, 2014

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalOctalNumberToken;

public class Python_OctalNumber extends TerminalOctalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar + 3 >= recLen) return false;	// Not long enough
		char ch = rec.charAt(_currentChar);
		if (ch != '0') return false;
		if (rec.charAt(_currentChar + 1) != 'o') return false;
		if (OCTAL.indexOf(rec.charAt(_currentChar + 2)) < 0) return false;
			
		int endChar = _currentChar + 2;
		while (true)
		{
			endChar++;
			if (endChar >= recLen) break;
			ch = rec.charAt(endChar);
			if (OCTAL.indexOf(ch) < 0)
			{
				// Allow a "long" suffix
				if (ch == 'L' || ch == 'l')
				{
					endChar++;
				}
				break;
			}
		}
		foundIt(_currentLine, endChar - 1);
		_numberAsText = rec.substring(_currentChar, endChar);
		return true;
	}
}
