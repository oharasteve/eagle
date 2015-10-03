// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class IntelASM_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public IntelASM_Comment()
	{
		this("");
	}
	
	public IntelASM_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (_currentChar >= nc || rec.charAt(_currentChar) != ';') return false;

		foundIt(_currentLine, nc);
		_comment = rec.substring(_currentChar, nc);
		return true;
	}
}
