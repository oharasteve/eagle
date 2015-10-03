// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class CSharp_CommentToEndOfLine extends TerminalCommentToken
{
	public CSharp_CommentToEndOfLine()
	{
		this("");
	}

	public CSharp_CommentToEndOfLine(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		_endChar = rec.length();
		foundIt(_currentLine, _endChar);
		_comment = rec.substring(_currentChar, _endChar);
		return true;
	}
}
