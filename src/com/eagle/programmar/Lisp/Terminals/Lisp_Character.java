// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

package com.eagle.programmar.Lisp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;


public class Lisp_Character extends TerminalLiteralToken
{
	private static final String[] specials = { "space" };
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);

		int recLen = rec.length();
		if (_currentChar + 2 >= recLen) return false;
		if (rec.charAt(_currentChar) != '#') return false;
		if (rec.charAt(_currentChar + 1) != '\\') return false;
		
		// Look for #\space
		for (String special : specials)
		{
			if (rec.charAt(_currentChar + 2) == special.charAt(0))
			{
				String piece = rec.substring(_currentChar + 2);
				if (piece.startsWith(special))
				{
					foundIt(_currentLine, _currentChar + special.length() - 1);
					return true;
				}
			}
		}

		foundIt(_currentLine, _currentChar + 2);
		return true;
	}
}
