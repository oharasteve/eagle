// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 25, 2013

package com.eagle.programmar.RPG.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalKeywordChoice;

public class RPG_KeywordChoice extends TerminalKeywordChoice
{
	private int fixedSc, fixedEc;
	
	// Need default constructor for reading from the XML file
	public RPG_KeywordChoice()
	{
		this(0, 0, new String[0]);
	}
	
	public RPG_KeywordChoice(int sc, int ec, String... words)
	{
		super(words);
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
		String piece = rec.substring(fixedSc, _endChar).trim();
		for (int i = 0; i < _words.length; i++)
		{
			if (piece.equalsIgnoreCase(_words[i]))
			{
				_which = i;
				foundIt(_currentLine, _endChar - 1);
				return true;
			}
		}
		return false;
	}
}
