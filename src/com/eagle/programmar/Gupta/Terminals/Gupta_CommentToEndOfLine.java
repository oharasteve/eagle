// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class Gupta_CommentToEndOfLine extends TerminalCommentToken
{
	public Gupta_CommentToEndOfLine()
	{
		this("");
	}
	
	public Gupta_CommentToEndOfLine(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		char ch = rec.charAt(_currentChar);
		if (ch != ':') return false;
		int nc = rec.length();
		foundIt(_currentLine, nc);
		_comment = rec.substring(_currentChar, nc);
		return true;
	}
}
