// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class CSharp_Literal extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		if (rec.charAt(_currentChar) == '@')
		{
			lines.setCurrentChar(_currentChar + 1);
			if (genericLiteral(lines, "\"", false, '?', true, true)) return true;
			lines.setCurrentChar(_currentChar);
			return false;
		}
		
		return genericLiteral(lines, "\"", true, '\\', false, false);
	}
}
