// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2012

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalHexNumberToken;

public class COBOL_HexNumber extends TerminalHexNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch = rec.charAt(_currentChar);
		if (ch != 'X' && ch != 'x') return false;
		if (_currentChar + 3 >= recLen) return false;	// Not long enough
		if (rec.charAt(_currentChar + 1) != '"') return false;
			
		int endChar = _currentChar + 2;
		while (true)
		{
			endChar++;
			if (endChar >= recLen) break;
			ch = rec.charAt(endChar);
			if (HEX.indexOf(ch) < 0) break;
		}
		if (rec.charAt(endChar) != '"') return false;
		foundIt(_currentLine, endChar);
		_numberAsText = rec.substring(_currentChar, endChar);
		return true;
	}
}
