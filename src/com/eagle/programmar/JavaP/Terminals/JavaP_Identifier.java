// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 26, 2016

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalIdentifierToken;

public class JavaP_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericIdentifier(lines, ALPHAS, ALPHAS+DIGITS, true);
	}
}
