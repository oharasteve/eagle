// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class CSharp_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public CSharp_Comment()
	{
		this("");
	}
	
	public CSharp_Comment(String comment)
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
		else if (ch == '#')
		{
			if (rec.contains("#region") || rec.contains("#endregion"))
			{
				foundIt(_currentLine, nc);
				_comment = rec.substring(_currentChar, nc);
				return true;
			}
		}
		
		return false;
	}
}
