// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;

public abstract class TerminalKeywordChoice extends TerminalToken
{
	protected String[] _words;
	protected int _which;
	
	public TerminalKeywordChoice()
	{
		_words = new String[0];
		_matchesConstant = true;
	}
	
	public TerminalKeywordChoice(String... words)
	{
		_words = words;
		_matchesConstant = true;
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		for (int i = 0; i < _words.length; i++)
		{
			String w = _words[i];
			if (matchWord(lines, w))
			{
				_which = i;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		if (_which >= 0 && _which < _words.length)
		{
			return _words[_which];
		}
		throw new RuntimeException("Keyword index out of range: " + _which + " (should be >= 0 and < " + _words.length + ")");
	}
	
	@Override
	public void setValue(String val)
	{
		if (_words == null || _words.length == 0)
		{
			// Must be coming from XML reader .. no way to know the value choices
			_words = new String[1];
			_words[0] = val;
			_which = 0;
			return;
		}

		// Oh well, look it up
		for (int i = 0; i < _words.length; i++)
		{
			if (_words[i].equals(val))
			{
				_which = i;
				return;
			}
		}
		
		throw new RuntimeException("Unable to set TerminalKeywordChoice to " + val);
	}

	@Override
	public String showString()
	{
		if (_words == null || _words.length == 0) return "null";
		
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < _words.length; i++)
		{
			sb.append("|\"");
			sb.append(_words[i]);
			sb.append("\"");
		}
		return "(\"" + _words[0] + '"' + sb.toString() + ')';
	}


	@Override
	public String description()
	{
		return null;
	}

	@Override
	public String getDisplayStyleName()
	{
		return "keyword";
	}
}
