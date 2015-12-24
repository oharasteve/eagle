	// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalIdentifierToken;

public class JavaP_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (! genericIdentifier(lines, "#", DIGITS, false)) return false;
		if (_id.length() < 2) return false;	// Need at least one digit
		return true;
	}
}
