// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalNumberToken;

public class PLI_Number extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", null, false);
	}
}
