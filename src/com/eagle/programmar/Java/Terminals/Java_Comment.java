// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.Java.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class Java_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public Java_Comment()
	{
		this("");
	}
	
	public Java_Comment(String comment, boolean hasEOLN)
	{
		super(comment, hasEOLN);
		_present = true;
	}
	
	public Java_Comment(String comment)
	{
		super(comment);
		_present = true;
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
