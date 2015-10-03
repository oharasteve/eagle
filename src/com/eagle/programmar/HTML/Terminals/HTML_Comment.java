// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.HTML.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class HTML_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public HTML_Comment()
	{
		this("");
	}
	
	public HTML_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		char ch = rec.charAt(_currentChar);
		if (ch == '<')
		{
			return super.possibleHtmlComment(lines, rec, "<!--", "-->");
		}
		return false;
	}
}
