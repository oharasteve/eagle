// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2015

package com.eagle.programmar.SQL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalHexNumberToken;
import com.eagle.tokens.TerminalLiteralToken;

public class SQL_HexString extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar + 2 >= recLen) return false;
		if (rec.charAt(_currentChar) != 'X') return false;
		if (rec.charAt(_currentChar + 1) != '\'') return false;

		int endChar = _currentChar + 2;
		while (true)
		{
			if (endChar >= recLen) return false;
			char ch = rec.charAt(endChar);
			if (TerminalHexNumberToken.HEX.indexOf(ch) < 0) break;
			endChar++;
		}

		if (endChar >= recLen) return false;
		if (rec.charAt(endChar) != '\'') return false;
			
		_txt = rec.substring(_currentChar, endChar);
		foundIt(_currentLine, endChar);
		return true;
	}
}