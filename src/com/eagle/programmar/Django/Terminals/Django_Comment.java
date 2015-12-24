// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2015

package com.eagle.programmar.Django.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class Django_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public Django_Comment()
	{
		this("");
	}
	
	public Django_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		return commentUntilSentinel(lines, rec, "{%");
	}
}
