// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class VB_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public VB_Comment()
	{
		this("");
	}
	
	public VB_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (_currentChar >= nc || rec.charAt(_currentChar) != '\'') return false;

		foundIt(_currentLine, nc);
		_comment = rec.substring(_currentChar, nc);
		return true;
	}
}
