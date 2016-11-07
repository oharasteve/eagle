// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 14, 2014

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;

public abstract class TerminalOctalNumberToken extends TerminalNumberToken
{
	protected static final String OCTAL = "01234567";

	public boolean genericOctal(EagleFileReader lines, String octalPrefix, String suffixChars)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		int prefixLen = octalPrefix.length();
		if (_currentChar + prefixLen >= recLen) return false;	// Not long enough
		int endChar = _currentChar;
		
		char ch = rec.charAt(endChar);
		if (ch == '+' || ch == '-') endChar++;
		
		for (int i = 0; i < prefixLen; i++)
		{
			ch = rec.charAt(endChar + i);
			if (ch != octalPrefix.charAt(i)) return false;
		}
		if (OCTAL.indexOf(rec.charAt(endChar + prefixLen)) < 0) return false;
			
		endChar += prefixLen;
		while (true)
		{
			endChar++;
			if (endChar >= recLen) break;
			ch = rec.charAt(endChar);
			if (OCTAL.indexOf(ch) < 0)
			{
				// Allow a "long" suffix
				if (suffixChars != null && suffixChars.indexOf(ch) >= 0)
				{
					endChar++;
				}
				
				// Fail if there is a digit above 7 next, or a letter
				if (endChar >= recLen) break;
				ch = rec.charAt(endChar);
				if (Character.isDigit(ch)) return false;
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
		return "Octal Number";
	}

	@Override
	public String description()
	{
		return "A octal number";
	}
}
