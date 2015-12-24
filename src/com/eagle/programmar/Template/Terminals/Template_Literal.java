// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalLiteralToken;

public class Template_Literal extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "\"'", true, '\\', false, false);
	}
}
