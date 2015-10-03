// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2014

package com.eagle.programmar.PLI.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class PLI_BitLiteral extends TerminalLiteralToken
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
			ch = rec.charAt(endChar);
			if (ch != '0' && ch != '1') break;
			endChar++;
		}

		if (endChar+1 >= recLen) return false;
		if (rec.charAt(endChar) != '\'') return false;
		if (Character.toUpperCase(rec.charAt(endChar+1)) != 'B') return false;
			
		_txt = rec.substring(_currentChar, endChar);
		foundIt(_currentLine, endChar+1);
		return true;
	}
	
	@Override
	public String showString()
	{
		return "Bit Literal";
	}

	@Override
	public String description()
	{
		return "A bit literal, like '0'B";
	}
}