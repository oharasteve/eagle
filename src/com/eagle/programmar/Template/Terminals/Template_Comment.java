// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class Template_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public Template_Comment()
	{
		this("");
	}
	
	public Template_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		if (_currentChar+1 < rec.length())
		{
			char ch1 = rec.charAt(_currentChar);
			if (ch1 == '/')
			{
				char ch2 = rec.charAt(_currentChar);
				if (ch2 == '/')
				{
					return super.possibleCommentToEndOfLine(rec, "//");
				}
				if (ch2 == '*')
				{
					return super.possibleCommentPair2(lines, rec, "/*", "*/");
				}
			}
		}
		return false;
	}
}
