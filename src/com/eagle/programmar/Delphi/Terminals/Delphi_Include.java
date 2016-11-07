// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2016

package com.eagle.programmar.Delphi.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class Delphi_Include extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public Delphi_Include()
	{
		super("");
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		char ch = rec.charAt(_currentChar);
		if (ch == '{')
		{
			if (! super.possibleCommentPair1(lines, rec, '{', '}')) return false;
			if (_comment.startsWith("{$I ")) return true;
			return false;
		}
		return false;
	}
}
