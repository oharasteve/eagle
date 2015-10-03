// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

package com.eagle.programmar.CSS.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class CSS_Base64 extends TerminalLiteralToken
{
	private static final String LEGAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		
		// Accept anything that looks like a filename
		int endChar = _currentChar;
		while (endChar < recLen)
		{
			char ch = rec.charAt(endChar);
			if (LEGAL_CHARS.indexOf(ch) < 0)
			{
				break;
			}
			endChar++;
		}
		_txt = rec.substring(_currentChar, endChar);
		foundIt(_currentLine, endChar - 1);
		return true;
	}
}
