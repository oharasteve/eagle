// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class PLI_Comment extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public PLI_Comment()
	{
		this("");
	}
	
	public PLI_Comment(String comment)
	{
		super(comment);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		if (rec.charAt(_currentChar) != '/') return false;
		return super.possibleCommentPair2(lines, rec, "/*", "*/");
	}
}
