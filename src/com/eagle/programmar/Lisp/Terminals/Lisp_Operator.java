// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalToken;

public class Lisp_Operator extends TerminalToken
{
	private String _oper;
	
	// Need default constructor for reading from the XML file
	public Lisp_Operator()
	{
		this("");
	}

	public Lisp_Operator(String oper)
	{
		_oper = oper;
	}
	
	// Can only be exactly one character, followed by whitespace
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);

		int recLen = rec.length();
		int operLen = _oper.length();
		if (_currentChar + operLen > recLen) return false;
		
		for (int i = 0; i < operLen; i++)
		{
			if (rec.charAt(_currentChar + i) != _oper.charAt(i))
			{
				return false;
			}
		}

		// Make sure there is whitespace in the next character
		if (_currentChar + operLen < recLen)
		{
			char ch2 = rec.charAt(_currentChar + operLen);
			if (ch2 != ' ' && ch2 != '\t' && ch2 != ')') return false;
		}
		
		foundIt(_currentLine, _currentChar + operLen - 1);
		return true;
	}

	@Override
	public String toString()
	{
		return _oper;
	}

	@Override
	public void setValue(String oper)
	{
		_oper = oper;
	}

	@Override
	public String showString()
	{
		return "'" + _oper + "'";
	}

	@Override
	public String getDisplayStyleName() 
	{
		return "punctuation";
	}

	@Override
	public String description()
	{
		return "Lisp oeprator";
	}
}
