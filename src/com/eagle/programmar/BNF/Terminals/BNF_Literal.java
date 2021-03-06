// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalLiteralToken;

public class BNF_Literal extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "'\"", true, '\\', false, false);
	}
}
