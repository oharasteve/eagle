// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.EagleSyntax;

public abstract class TerminalPunctuationChoice extends TerminalToken
{
	protected TerminalPunctuationToken[] _puncts = null;
	protected int _which;
	
	public TerminalPunctuationChoice()
	{
		_puncts = null;
	}
	
	public TerminalPunctuationChoice(String... puncts)
	{
		_matchesConstant = true;
		int len = puncts.length;
		_puncts = new TerminalPunctuationToken[len];
		for (int i = 0; i < len; i++)
		{
			_puncts[i] = new TerminalPunctuationToken(puncts[i]);
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
		
		for (int i = 0; i < _puncts.length; i++)
		{
			TerminalPunctuationToken punct = _puncts[i];
			if (punct.match(rec, recLen, _currentChar, syntax))
			{
				_which = i;
				foundIt(_currentLine, _currentChar + punct._len - 1);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return _puncts[_which].toString();
	}
	
	@Override
	public void setValue(String val)
	{
		if (_puncts == null || _puncts.length == 0) // Must be coming from XML reader ...
		{
			_puncts = new TerminalPunctuationToken[1];
			_puncts[0] = new TerminalPunctuationToken(val);
			_which = 0;
			return;
		}

		// Oh well, look it up
		for (int i = 0; i < _puncts.length; i++)
		{
			if (_puncts[i].toString().equals(val))
			{
				_which = i;
				return;
			}
		}
		
		throw new RuntimeException("Unable to set TerminalPunctuationChoice to " + val);
	}

	@Override
	public String showString()
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < _puncts.length; i++)
		{
			if (i > 0) sb.append('|');
			sb.append("\"");
			sb.append(_puncts[i].toString());
			sb.append("\"");
		}
		return '(' + sb.toString() + ')';
	}
}
