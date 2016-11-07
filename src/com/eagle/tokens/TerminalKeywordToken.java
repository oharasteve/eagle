// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2011

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;


public abstract class TerminalKeywordToken extends TerminalToken
{
	protected String _word;
	
	public TerminalKeywordToken(String word)
	{
		_word = word;
		_matchesConstant = true;
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return matchWord(lines, _word);
	}

	@Override
	public String toString()
	{
		return _word;
	}

	@Override
	public void setValue(String val)
	{
		_word = val;
		_present = (val != null);
	}

	@Override
	public String showString()
	{
		return '"' + _word + '"';
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
