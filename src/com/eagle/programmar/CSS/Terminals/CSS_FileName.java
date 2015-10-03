// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2011

package com.eagle.programmar.CSS.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;


public class CSS_FileName extends TerminalLiteralToken
{
	private static String ALLOWED = ":-/_.#?";
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		char ch = rec.charAt(_currentChar);
		if (ch == '\'' || ch == '"')
		{
			return genericLiteral(lines, "\"'", true, '\\', false, false);
		}
		
		// Accept anything that looks like a filename
		int endChar = _currentChar;
		while (endChar < recLen)
		{
			ch = rec.charAt(endChar);
			if (ALLOWED.indexOf(ch) < 0 && !Character.isLetterOrDigit(ch))
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
