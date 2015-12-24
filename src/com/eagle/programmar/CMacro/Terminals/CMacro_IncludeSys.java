// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 26, 2015

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalLiteralToken;

public class CMacro_IncludeSys extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return quotePair(lines, '<', '>');
	}
}
