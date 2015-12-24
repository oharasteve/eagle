// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class JavaP_QualifiedName extends TerminalLiteralToken
{
	private static final String VALIDS = "/-._$";	// Valid characters in the name
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		
		int endChar = _currentChar;
		while (endChar < recLen)
		{
			char ch = rec.charAt(endChar);
			if (!Character.isLetterOrDigit(ch) && VALIDS.indexOf(ch) < 0)
			{
				// Not a valid filename character
				break;
			}
			endChar++;
		}

		if (endChar == _currentChar) return false;	// Zero length -> fail
		foundIt(_currentLine, endChar-1);
		_txt = rec.substring(_currentChar, endChar);
		return true;
	}
}
