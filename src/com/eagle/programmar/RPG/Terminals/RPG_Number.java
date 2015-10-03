// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalNumberToken;

public class RPG_Number extends TerminalNumberToken
{
	private int fixedSc, fixedEc;
	
	// Used by XML Reader ...
	public RPG_Number()
	{
		this(0, 0);
	}
	
	public RPG_Number(int sc, int ec)
	{
		fixedSc = sc - 1;
		fixedEc = ec;
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		EagleLineReader rec = lines.get(_currentLine);
		_endChar = rec.length();
		if (_endChar < fixedSc || fixedSc < 0) return false;	// Too short
		if (_endChar > fixedEc) _endChar = fixedEc;
		_numberAsText = rec.substring(fixedSc, _endChar).trim();
		if (_numberAsText.length() == 0) return false;	// No number there
		foundIt(_currentLine, _endChar - 1);
		return true;
	}
}
