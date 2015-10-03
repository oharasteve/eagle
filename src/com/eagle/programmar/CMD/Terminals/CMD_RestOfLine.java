// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class CMD_RestOfLine extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		_currentLine = lines.getCurrentLine();
		_currentChar = lines.getCurrentChar();
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		foundIt(_currentLine, recLen);
		_txt = rec.substring(_currentChar, recLen);
		return true;
	}

	@Override
	public String description()
	{
		return "Rest of the line";
	}
}
