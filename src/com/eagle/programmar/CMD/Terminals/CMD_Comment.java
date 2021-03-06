// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2012

package com.eagle.programmar.CMD.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class CMD_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public CMD_Comment()
	{
		this("");
	}
	
	public CMD_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		if (_currentChar >= rec.length()) return false;
		char ch = rec.charAt(_currentChar);
		if (ch == ':')
		{
			return super.possibleCommentToEndOfLine(rec, "::");
		}
		return false;
	}
}
