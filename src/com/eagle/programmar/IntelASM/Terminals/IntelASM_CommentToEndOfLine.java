// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class IntelASM_CommentToEndOfLine extends TerminalCommentToken
{
	public IntelASM_CommentToEndOfLine()
	{
		this("");
	}

	public IntelASM_CommentToEndOfLine(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int ec = rec.length();
		foundIt(_currentLine, ec);
		_comment = rec.substring(_currentChar, ec);
		return true;
	}
}
