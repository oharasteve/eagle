// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

package com.eagle.programmar.Natural.Terminals;

import java.util.HashSet;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalIdentifierToken;

public abstract class Natural_Identifier extends TerminalIdentifierToken
{
	public static HashSet<String> Natural_keywords = new HashSet<String>();
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericIdentifier(lines, ALPHAS+"#", ALPHAS+DIGITS+"-", true);
	}
}
