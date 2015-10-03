// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

package com.eagle.programmar.IBMASM.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalIdentifierToken;

public class IBMASM_Register extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		char ch = rec.charAt(_currentChar);
		if (("Rr"+DIGITS).indexOf(ch) >= 0)
		{
			int endChar = _currentChar;
			while (true)
			{
				endChar++;
				if (endChar >= recLen) break;
				ch = rec.charAt(endChar);
				if (DIGITS.indexOf(ch) < 0) break;
			}
			
			_id = rec.substring(_currentChar, endChar);
			foundIt(_currentLine, endChar - 1);
			return true;
		}
		return false;
	}
}
