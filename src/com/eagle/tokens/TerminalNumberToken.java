// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2011

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;


public abstract class TerminalNumberToken extends TerminalToken implements EagleRunnable
{
	protected String _numberAsText;
	
	@Override
	public String getDisplayStyleName()
	{
		return "number";
	}
	
	protected boolean genericNumber(EagleFileReader lines, String hexChars, String exponentChars,
			String suffixChars, boolean allowTrailingPeriod)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		char ch1 = rec.charAt(_currentChar);
		char ch2 = '?';
		char ch3 = '?';
		if (_currentChar+1 < recLen) ch2 = rec.charAt(_currentChar+1);
		if (_currentChar+2 < recLen) ch3 = rec.charAt(_currentChar+2);
		
		if (Character.isDigit(ch1) ||
				((ch1 == '+' || ch1 == '-' || ch1 == '.') && Character.isDigit(ch2)))
		{
			if (hexChars != null)
			{
				if (ch1 == '0' && hexChars.indexOf(ch2) >= 0) return false;	// Hex
				if (ch1 == '-' && ch2 == '0' && hexChars.indexOf(ch3) >= 0) return false;	// Hex
			}
			
			int endChar = _currentChar;
			boolean foundExponent = false;
			boolean foundDecimalPoint = false;
			while (true)
			{
				endChar++;
				if (endChar >= recLen) break;
				char ch = rec.charAt(endChar);
				if (!Character.isDigit(ch))
				{
					// Don't allow a decimal point if:
					// 1. already found one
					// 2. Already found an exponent
					// 3. !allowTrailingPeriod AND next character is not a digit
					// 4. next char is also a decimal point
					if (!foundDecimalPoint && !foundExponent && ch == '.' &&
							(allowTrailingPeriod || (endChar+1 < recLen && Character.isDigit(rec.charAt(endChar+1)))) &&
							(endChar+1 >= recLen || rec.charAt(endChar+1) != '.'))
					{
						foundDecimalPoint = true;
						continue;
					}
					
					if (exponentChars != null)
					{
						if (!foundExponent && exponentChars.indexOf(ch) >= 0)
						{
							if (endChar+1 < recLen)
							{
								ch = rec.charAt(endChar+1);
								if (ch == '+' || ch == '-') endChar++;
							}
							foundExponent = true;
							continue;
						}
					}

					// Might have an L suffix for Long, or F for float
					if (suffixChars != null)
					{
						if (suffixChars.indexOf(ch) >= 0) endChar++;
					}
					
					break;
				}
			}
			foundIt(_currentLine, endChar - 1);
			_numberAsText = rec.substring(_currentChar, endChar);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return _numberAsText;
	}
	
	@Override
	public void setValue(String val)
	{
		_numberAsText = val;
	}
	
	@Override
	public String getValue()
	{
		return _numberAsText;
	}
	
	@Override
	public String showString()
	{
		return "Number";
	}

	@Override
	public String description()
	{
		return "A number";
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int value = Integer.parseInt(_numberAsText);
		interpreter.pushInt(value);
	}
}
