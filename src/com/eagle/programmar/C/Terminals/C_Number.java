// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalNumberToken;

public class C_Number extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "Lf", true);
	}
}
