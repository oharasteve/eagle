// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalIdentifierToken;

public abstract class Perl_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (genericIdentifierWithPrefix(lines, "$#", ALPHAS+"_", ALPHAS+DIGITS+"_")) return true;
		return genericIdentifier(lines, ALPHAS+"_", ALPHAS+DIGITS+"_", true);
	}
}
