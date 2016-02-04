// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 23, 2014

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;

public abstract class TerminalHexNumberToken extends TerminalNumberToken
{
	public static final String HEX = "0123456789ABCDEFabcdef";

	public boolean genericHex(EagleFileReader lines, String hexPrefix, String suffixChars)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		int prefixLen = hexPrefix.length();
		if (_currentChar + prefixLen >= recLen) return false;	// Not long enough
		for (int i = 0; i < prefixLen; i++)
		{
			char ch = rec.charAt(_currentChar + i);
			if (ch != hexPrefix.charAt(i)) return false;
		}
		if (HEX.indexOf(rec.charAt(_currentChar + prefixLen)) < 0) return false;
			
		int endChar = _currentChar + prefixLen;
		while (true)
		{
			endChar++;
			if (endChar >= recLen) break;
			char ch = rec.charAt(endChar);
			if (HEX.indexOf(ch) < 0)
			{
				// Allow a "long" suffix
				if (suffixChars != null && suffixChars.indexOf(ch) >= 0)
				{
					endChar++;
				}
				
				// Fail if there is a letter next, above A-F
				if (endChar >= recLen) break;
				ch = rec.charAt(endChar);
				if (Character.isAlphabetic(ch)) return false;
				
				break;
			}
		}
		foundIt(_currentLine, endChar - 1);
		_numberAsText = rec.substring(_currentChar, endChar);
		return true;
	}
	
	@Override
	public String showString()
	{
		return "Hex Number";
	}

	@Override
	public String description()
	{
		return "A hex number";
	}
}
