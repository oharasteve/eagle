// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 8, 2014

package com.eagle.programmar.Perl.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalOctalNumberToken;

public class Perl_OctalNumber extends TerminalOctalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar + 2 >= recLen) return false;	// Not long enough
		char ch = rec.charAt(_currentChar);
		if (ch != '0') return false;
		if (OCTAL.indexOf(rec.charAt(_currentChar + 1)) < 0) return false;
			
		int endChar = _currentChar + 1;
		while (true)
		{
			endChar++;
			if (endChar >= recLen) break;
			ch = rec.charAt(endChar);
			if (OCTAL.indexOf(ch) < 0)
			{
				break;
			}
		}
		foundIt(_currentLine, endChar - 1);
		_numberAsText = rec.substring(_currentChar, endChar);
		return true;
	}
}
