// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalHexNumberToken;

public class JavaP_HexNumber extends TerminalHexNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericHex(lines, "0x", "");
	}
	
	public static class JavaP_HexNoPrefix extends TerminalHexNumberToken
	{
		@Override
		public boolean parse(EagleFileReader lines)
		{
			return genericHex(lines, "", "");
		}
	}
}
