// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class Delphi_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public Delphi_Comment()
	{
		this("");
	}
	
	public Delphi_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		char ch = rec.charAt(_currentChar);
		switch (ch)
		{
		case '{' :
			if (! super.possibleCommentPair1(lines, rec, '{', '}')) return false;
			if (_comment.startsWith("{$I ")) return false;
			return true;
		case '/' :
			return super.possibleCommentToEndOfLine(rec, "//");
		case '(' :
			return super.possibleCommentPair2(lines, rec, "(*", "*)");
		}
		return false;
	}
}
