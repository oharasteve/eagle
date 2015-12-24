// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalNumberToken;

public class JavaP_Number extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "x", "", "ld", false);
	}
}
