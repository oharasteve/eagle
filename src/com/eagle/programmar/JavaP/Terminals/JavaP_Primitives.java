// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 25, 2015

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class JavaP_Primitives extends TerminalLiteralToken
{
	private static final String PRIMITIVES = "BCDIJVZ";
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		char ch = rec.charAt(_currentChar);
		if (PRIMITIVES.indexOf(ch) < 0) return false;
		
		int endChar = _currentChar + 1;
		while (endChar < recLen)
		{
			ch = rec.charAt(endChar);
			if (PRIMITIVES.indexOf(ch) < 0)
			{
				if (Character.isLetterOrDigit(ch)) return false;
				endChar--;
				break;
			}
			endChar++;
		}

		foundIt(_currentLine, endChar);
		_txt = rec.substring(_currentChar, endChar);
		return true;
	}
}
