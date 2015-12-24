// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2011

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.EagleSyntax;


public class TerminalPunctuationToken extends TerminalToken
{
	protected int _len;
	protected char _punct1;
	protected char _punct2;
	protected char _punct3;
	protected char _punct4;
	
	public TerminalPunctuationToken(char punct)
	{
		_matchesConstant = true;
		_len = 1;
		_punct1 = punct;
	}
	
	public TerminalPunctuationToken(String punct)
	{
		_matchesConstant = true;
		_len = punct.length();
		switch (_len)
		{
		case 4:
			_punct4 = punct.charAt(3);
			//$FALL-THROUGH$
		case 3:
			_punct3 = punct.charAt(2);
			//$FALL-THROUGH$
		case 2:
			_punct2 = punct.charAt(1);
			//$FALL-THROUGH$
		case 1:
			_punct1 = punct.charAt(0);
			break;
		default:
			throw new RuntimeException("Cannot have punctuation with " + _len + " characters: " + punct);
		}
	}
	
	@Override
	public String getDisplayStyleName()
	{
		return "punctuation";
	}

	@Override
	public String description()
	{
		return null;
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		EagleSyntax syntax = this.getSyntax();
		if (!match(rec, recLen, _currentChar, syntax)) return false;
		foundIt(_currentLine, _currentChar + _len - 1);
		return true;
	}
	
    boolean match(EagleLineReader rec, int recLen, int sc, EagleSyntax syntax)
	{
		switch (_len)
		{
		case 1:
			if (sc >= recLen) return false;
			if (rec.charAt(sc) != _punct1) return false;
			break;
		case 2:
			if (sc+1 >= recLen) return false;
			if (rec.charAt(sc) != _punct1) return false;
			if (rec.charAt(sc+1) != _punct2) return false;
			break;
		case 3:
			if (sc+2 >= recLen) return false;
			if (rec.charAt(sc) != _punct1) return false;
			if (rec.charAt(sc+1) != _punct2) return false;
			if (rec.charAt(sc+2) != _punct3) return false;
			break;
		case 4:
			if (sc+3 >= recLen) return false;
			if (rec.charAt(sc) != _punct1) return false;
			if (rec.charAt(sc+1) != _punct2) return false;
			if (rec.charAt(sc+2) != _punct3) return false;
			if (rec.charAt(sc+3) != _punct4) return false;
			break;
		default:
			return false;
		}

		// Make sure there isn't an = in the next character, following a check for '='
		// For <= >= == != protection
		if (syntax._punctuationExceptions != null)
		{
			for (String except : syntax._punctuationExceptions)
			{
				int exceptLen = except.length();
				switch (exceptLen)
				{
				case 2:
					if (sc+1 < recLen)
					{
						if (_len == 1 && _punct1 == except.charAt(0))
						{
							if (rec.charAt(sc+1) == except.charAt(1)) return false;
						}
					}
					break;
				case 3:
					if (sc+2 < recLen)
					{
						if (_len == 1 && _punct1 == except.charAt(0))
						{
							if (rec.charAt(sc+1) == except.charAt(1) && rec.charAt(sc+2) == except.charAt(2)) return false;
						}
						else if (_len == 2 && _punct1 == except.charAt(0) && _punct2 == except.charAt(1))
						{
							if (rec.charAt(sc+2) == except.charAt(2)) return false;
						}
					}
					break;
				case 4:
					if (sc+3 < recLen)
					{
						if (_len == 1 && _punct1 == except.charAt(0))
						{
							if (rec.charAt(sc+1) == except.charAt(1) && rec.charAt(sc+2) == except.charAt(2) && rec.charAt(sc+3) == except.charAt(3)) return false;
						}
						else if (_len == 2 && _punct1 == except.charAt(0) && _punct2 == except.charAt(1))
						{
							if (rec.charAt(sc+2) == except.charAt(2) && rec.charAt(sc+3) == except.charAt(3)) return false;
						}
						else if (_len == 3 && _punct1 == except.charAt(0) && _punct2 == except.charAt(1) && _punct3 == except.charAt(2))
						{
							if (rec.charAt(sc+3) == except.charAt(3)) return false;
						}
					}
					break;
				default:
					// Only allow strings of length 2,3,4 in the exception list
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public String toString()
	{
		switch (_len)
		{
		case 1:
			return Character.toString(_punct1);
		case 2:
			return Character.toString(_punct1) + Character.toString(_punct2);
		case 3:
			return Character.toString(_punct1) + Character.toString(_punct2) +
					Character.toString(_punct3);
		case 4:
			return Character.toString(_punct1) + Character.toString(_punct2) +
					Character.toString(_punct3) + Character.toString(_punct4);
		default:
			return "Error";
		}
	}
	
	@Override
	public void setValue(String val0)
	{
		String val = val0;
		if (val.startsWith("'") && val.endsWith("'") && val.length() > 1)
		{
			val = val.substring(1, val.length()-1);
		}

		_len = val.length();
		switch (_len)
		{
		case 1:
			_punct1 = val.charAt(0);
			break;
		case 2:
			_punct1 = val.charAt(0);
			_punct2 = val.charAt(1);
			break;
		case 3:
			_punct1 = val.charAt(0);
			_punct2 = val.charAt(1);
			_punct3 = val.charAt(2);
			break;
		case 4:
			_punct1 = val.charAt(0);
			_punct2 = val.charAt(1);
			_punct3 = val.charAt(2);
			_punct4 = val.charAt(3);
			break;
		default:
			throw new RuntimeException("TerminalPunctuation must be 1-4 characters long, not \"" + val + '\"');
		}
	}
	
	@Override
	public String showString()
	{
		switch (_len)
		{
		case 1:
			return "'" + _punct1 + "'";
		case 2:
			return "\"" + _punct1 + _punct2 + "\"";
		case 3:
			return "\"" + _punct1 + _punct2 + _punct3 + "\"";
		case 4:
			return "\"" + _punct1 + _punct2 + _punct3 + _punct4 + "\"";
		default:
			return "Error";
		}
	}
}