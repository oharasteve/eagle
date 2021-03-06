// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TerminalNumberToken;

public class Java_Number extends TerminalNumberToken implements EagleRunnable
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "LlFfDd", true);
	}
}
