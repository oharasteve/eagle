// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.EagleSyntax;
import com.eagle.tokens.TerminalCommentToken;

public class COBOL_CommentToEndOfLine extends TerminalCommentToken
{
	public COBOL_CommentToEndOfLine()
	{
		this("");
	}

	public COBOL_CommentToEndOfLine(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleSyntax syntax = getSyntax();
		EagleLineReader rec = lines.get(_currentLine);
		_endChar = syntax.recLen(lines, _currentLine);
		foundIt(_currentLine, _endChar);
		if (_currentChar < syntax._earliestComment) return false;		// Comments like this must start in column 12 or later
		_comment = rec.substring(_currentChar, _endChar);
		return true;
	}
}
