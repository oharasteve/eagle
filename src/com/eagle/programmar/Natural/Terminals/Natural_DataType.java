// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 10, 2011

package com.eagle.programmar.Natural.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalPictureToken;

public class Natural_DataType extends TerminalPictureToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		
		// (D)
		char ch1 = rec.charAt(_currentChar);
		if (ch1 == 'D')
		{
			_endChar = _currentChar;
			foundIt(_currentLine, _endChar);
			_pic = rec.substring(_currentChar, _endChar);
			return true;
		}

		// (A3) (N2) (N.3)
		if (_currentChar + 1 >= recLen) return false;
		char ch2 = rec.charAt(_currentChar + 1);
		if ((ch1 == 'A' || ch1 == 'N') && (Character.isDigit(ch2) || ch2 == '.'))
		{
			_endChar = _currentChar + 1;
			while (++_endChar < recLen)
			{
				char ch = rec.charAt(_endChar);
				if (!Character.isDigit(ch) && ch != '.') break;
			}
			foundIt(_currentLine, _endChar-1);
			_pic = rec.substring(_currentChar, _endChar-1);
			return true;
		}

		// (P9/2)
		if ((ch1 == 'P') && Character.isDigit(ch2))
		{
			_endChar = _currentChar + 1;
			while (++_endChar < recLen)
			{
				char ch = rec.charAt(_endChar);
				if (!Character.isDigit(ch) && ch != '/') break;
			}
			foundIt(_currentLine, _endChar-1);
			_pic = rec.substring(_currentChar, _endChar-1);
			return true;
		}

		return false;
	}

	@Override
	public String description()
	{
		return "Natural data type, such as A21 or N2.";
	}
}
