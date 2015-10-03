// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 10, 2014

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class Javascript_RegularExpression extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		if (!genericLiteral(lines, "/", true, '\\', false, false)) return false;
		
		// Don't pick up a comment instead of a regular expression!
		if (_txt.length() >= 2)
		{
			char ch = _txt.charAt(1);
			if (ch == '/' || ch == '*')
			{
				return false;	// Both "//" and "/*" are comments
			}
		}

		// Check for modifiers
		EagleLineReader rec = lines.get(_endLine);
		int recLen = rec.length();
		while (_endChar+1 < recLen)
		{
			char ch = rec.charAt(_endChar+1);
			// ignore case, global, multiline modifiers
			if (ch != 'i' && ch != 'g' && ch != 'm') break;
			_txt += ch;
			_endChar++;
		}
		
		return true;
	}
}
