// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

package com.eagle.programmar.IBMASM.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class IBMASM_Spaces extends TerminalLiteralToken
{
	private String _spaces;
	
	// Need a default constructor for the parser
	public IBMASM_Spaces()
	{
		this("");
	}
	
	public IBMASM_Spaces(String spaces)
	{
		_spaces = spaces;
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		_endChar = _currentChar;
		while (_endChar+1 < recLen && rec.charAt(_endChar+1) == ' ') _endChar++;
		_spaces = rec.substring(_currentChar, _endChar);
		foundIt(_currentLine, _endChar);
		return true;
	}

	@Override
	public String toString()
	{
		return _spaces;
	}
	
	@Override
	public void setValue(String val)
	{
		_spaces = val;
		_present = true;
	}
	
	@Override
	public String showString()
	{
		return "Spaces";
	}

	@Override
	public String description()
	{
		return "IBMASM spaces";
	}
}
