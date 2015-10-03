// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalLiteralToken;

public class Javascript_Literal extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "\"'", true, '\\', false, false);
	}
}
