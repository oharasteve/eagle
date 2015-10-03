// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class Natural_EditMask extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch = rec.charAt(_currentChar);
		int parenCount = 0;
		boolean inQuotes = ch == '\'';
		if (ch != ')')
		{
			int endChar = _currentChar;
			while (true)
			{
				endChar++;
				if (endChar >= recLen) return false;
				ch = rec.charAt(endChar);
				if (ch == '\'') inQuotes = ! inQuotes;
				if (!inQuotes && ch == '(') parenCount++;
				if (!inQuotes && ch == ')')
				{
					if (parenCount == 0) break;
					parenCount--;
				}
			}
			foundIt(_currentLine, endChar - 1);
			_txt = rec.substring(_currentChar, endChar);
			return true;
		}
		return false;
	}
	
	@Override
	public String showString()
	{
		return "Edit Mask";
	}

	@Override
	public String description()
	{
		return "An edit mask, such as (EM=YYYY-MM-DD)";
	}
}