// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalHexNumberToken;

public class CSharp_HexNumber extends TerminalHexNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericHex(lines, "0x", "Ll");
	}
}
