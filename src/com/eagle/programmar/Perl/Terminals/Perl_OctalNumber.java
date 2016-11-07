// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 8, 2014

package com.eagle.programmar.Perl.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalOctalNumberToken;

public class Perl_OctalNumber extends TerminalOctalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericOctal(lines, "0", null);
	}
}
