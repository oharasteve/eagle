// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class Javascript_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public Javascript_Comment()
	{
		this("");
	}
	
	public Javascript_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (_currentChar + 1 >= nc) return false;
		if (rec.charAt(_currentChar) != '/') return false;

		char ch = rec.charAt(_currentChar + 1);
		switch (ch)
		{
		case '/' :
			return super.possibleCommentToEndOfLine(rec, "//");
		case '*' :
			return super.possibleCommentPair2(lines, rec, "/*", "*/");
		}
		return false;
	}
}
