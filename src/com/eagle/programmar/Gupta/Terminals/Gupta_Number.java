// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalNumberToken;

public class Gupta_Number extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, null, null, false);
	}
}
