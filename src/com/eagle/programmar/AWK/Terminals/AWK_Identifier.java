// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalIdentifierToken;

public class AWK_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (genericIdentifier(lines, ALPHAS+"_", ALPHAS+DIGITS+"_", false)) return true;
		
		// Check for $1 etc
		if (genericIdentifier(lines, "$", ALPHAS+DIGITS+"_", false))
		{
			if (_id.length() > 1) return true;
		}
		
		return false;
	}
}
