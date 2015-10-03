// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

package com.eagle.programmar.PLI.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalHexNumberToken;

public class PLI_HexNumber extends TerminalHexNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch = rec.charAt(_currentChar);
		if (ch != '\'') return false;

		int endChar = _currentChar + 1;
		while (true)
		{
			if (endChar >= recLen) return false;
			ch = Character.toUpperCase(rec.charAt(endChar));
			if (HEX.indexOf(ch) < 0) break;
			endChar++;
		}

		if (endChar+1 >= recLen) return false;
		if (rec.charAt(endChar) != '\'') return false;
		if (Character.toUpperCase(rec.charAt(endChar+1)) != 'X') return false;
			
		_numberAsText = rec.substring(_currentChar, endChar);
		foundIt(_currentLine, endChar+1);
		return true;
	}
}
