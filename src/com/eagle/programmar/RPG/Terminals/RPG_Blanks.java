// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 26, 2011

package com.eagle.programmar.RPG.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class RPG_Blanks extends TerminalLiteralToken
{
	private int fixedSc, fixedEc;
	
	// Used by XML Reader ...
	public RPG_Blanks()
	{
		this(0, 0);
	}
	
	public RPG_Blanks(int sc, int ec)
	{
		fixedSc = sc - 1;
		fixedEc = ec;
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		EagleLineReader rec = lines.get(_currentLine);
		_endChar = rec.length();
		if (_endChar < fixedSc || fixedSc < 0)
		{
			_txt = "";
			foundIt(_currentLine, _endChar - 1);
			return true;	// Too short means blanks
		}
		if (_endChar > fixedEc) _endChar = fixedEc;
		_txt = rec.substring(fixedSc, _endChar).trim();
		if (_txt.length() > 0) return false;	// Means something is there, other than blanks
		foundIt(_currentLine, _endChar - 1);
		return true;
	}
}
