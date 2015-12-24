// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class JavaP_LClassName extends TerminalLiteralToken
{
	private static final String VALIDS = "/_$";	// Valid characters in the name

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		char ch = rec.charAt(_currentChar);
		if (ch != 'L') return false;
		
		int endChar = _currentChar + 1;
		while (endChar < recLen)
		{
			ch = rec.charAt(endChar);
			if (!Character.isLetterOrDigit(ch) && VALIDS.indexOf(ch) < 0)
			{
				// Not a valid classname character
				if (ch != ';' && ch != '<') return false;	// Must end with a semicolon eventually
				endChar--;	// Don't include the ; or <
				break;
			}
			endChar++;
		}
		if (_endChar == _currentChar) return false;	// Nothing there, just L; or L<

		foundIt(_currentLine, endChar);
		_txt = rec.substring(_currentChar, endChar);
		return true;
	}
}
