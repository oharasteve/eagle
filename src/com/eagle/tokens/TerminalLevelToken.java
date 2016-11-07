// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;

public abstract class TerminalLevelToken extends TerminalToken
{
	public int _level;
	public static final boolean DEBUG = false;

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch1 = rec.charAt(_currentChar);
		if (Character.isDigit(ch1))
		{
			_endChar = _currentChar;
			if (_endChar+1 < recLen)
			{
				char ch2 = rec.charAt(_endChar+1);
				if (Character.isDigit(ch2)) _endChar++;
				
				// Still a third digit? Can't be a level number
				if (_endChar+1 < recLen)
				{
					char ch3 = rec.charAt(_endChar+1);
					if (Character.isDigit(ch3)) return false;
				}
			}
			String txt = rec.substring(_currentChar, _endChar+1);
			_level = Integer.parseInt(txt);
			if (_level == 0) return false;
			if (DEBUG) System.out.println((_currentLine+1) + " *********************** checking level " + _level);
			
			if (validateLevel())
			{
				foundIt(_currentLine, _endChar);
				if (DEBUG) System.out.println((_currentLine+1) + " matched level " + _level);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String getDisplayStyleName()
	{
		return "number";
	}

	protected abstract boolean validateLevel();

	@Override
	public String toString()
	{
		return Integer.toString(_level);
	}
	
	@Override
	public String getValue()
	{
		return toString();
	}

	@Override
	public void setValue(String val)
	{
		if (val.length() == 0)
		{
			_level = 0;
		}
		else
		{
			_level = Integer.parseInt(val);
		}
	}
	
	@Override
	public String showString()
	{
		return "Level_Number";
	}
}
