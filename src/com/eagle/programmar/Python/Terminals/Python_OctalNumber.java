// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 14, 2014

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalOctalNumberToken;

public class Python_OctalNumber extends TerminalOctalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericOctal(lines, "0o", "Ll");
	}
}
