// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.CSS.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class CSS_Comment extends TerminalCommentToken
{
	//private boolean isHtmlStyle;
	
	// Need a default constructor for the parser
	public CSS_Comment()
	{
		this("");
	}
	
	public CSS_Comment(String comment)
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
		char ch = rec.charAt(_currentChar);
		if (ch == '/')
		{
			ch = rec.charAt(_currentChar + 1);
			switch (ch)
			{
			case '/' :
				return super.possibleCommentToEndOfLine(rec, "//");
			case '*' :
				return super.possibleCommentPair2(lines, rec, "/*", "*/");
			}
		}
		if (ch == '<')
		{
			return super.possibleHtmlComment(lines, rec, "<!--", "-->");
		}
		return false;
	}
}
