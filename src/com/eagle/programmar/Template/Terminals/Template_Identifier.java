// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalIdentifierToken;

public class Template_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericIdentifier(lines, ALPHAS+"_", ALPHAS+DIGITS+"_", true);
	}
}
