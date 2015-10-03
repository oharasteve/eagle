// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 1, 2014

package com.eagle.programmar.Perl.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalRegularExpression;
import com.eagle.tokens.TokenChooser;

public class Perl_RegularExpression extends TokenChooser
{
	public static class Perl_RegularSubstitution extends TerminalRegularExpression
	{
		@Override
		public boolean parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar+2 >= recLen) return false;
			
			char ch = rec.charAt(_currentChar);
			if (ch == 's')
			{
				char marker = rec.charAt(_currentChar + 1);
				if (marker == '/')
				{
					int middle = myIndexOf(rec, marker, _currentChar + 2);
					if (middle > 0)
					{
						int endChar = myIndexOf(rec, marker, middle + 1);
						if (endChar > 0)
						{
							if (endChar + 1 < recLen)
							{
								if (rec.charAt(endChar+1) == 'g') endChar++;
								foundIt(_currentLine, endChar);
								return true;
							}
						}
					}
				}
			}
			
			return false;
		}
	}
	
	public static class Perl_RegularTranslation extends TerminalRegularExpression
	{
		@Override
		public boolean parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar+3 >= recLen) return false;
			
			char ch = rec.charAt(_currentChar);
			if (ch == 't')
			{
				ch = rec.charAt(_currentChar + 1);
				if (ch == 'r')
				{
					char marker = rec.charAt(_currentChar + 2);
					if (marker == '/')
					{
						int middle = myIndexOf(rec, marker, _currentChar + 3);
						if (middle > 0)
						{
							int endChar = myIndexOf(rec, marker, middle + 1);
							if (endChar > 0)
							{
								if (endChar + 1 < recLen)
								{
									foundIt(_currentLine, endChar);
									return true;
								}
							}
						}
					}
				}
			}
			
			return false;
		}
	}
	
	public static class Perl_RegularCondition extends TerminalRegularExpression
	{
		@Override
		public boolean parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar+2 >= recLen) return false;
			
			char marker = rec.charAt(_currentChar);
			if (marker == '/')
			{
				int endChar = myIndexOf(rec, marker, _currentChar + 1);
				if (endChar > 0)
				{
					if (endChar + 1 < recLen)
					{
						if (rec.charAt(endChar+1) == 'i') endChar++;
						foundIt(_currentLine, endChar);
						return true;
					}
				}
			}
			
			return false;
		}
	}
	
	static int myIndexOf(EagleLineReader rec, char marker, int sc)
	{
		int len = rec.length();
		char prev = '?';
		for (int i = sc; i < len; i++)
		{
			char ch = rec.charAt(i);
			if (ch == marker && prev != '\\') return i;		// Found it!
			prev = ch;
		}
		return -1;	// Not found
	}
}