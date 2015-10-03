// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class AWK_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public AWK_Comment()
	{
		this("");
	}
	
	public AWK_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (_currentChar >= nc) return false;
		if (rec.charAt(_currentChar) != '#') return false;
		foundIt(_currentLine, nc);
		_comment = rec.substring(_currentChar, nc);
		return true;
	}
}
