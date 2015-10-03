// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

package com.eagle.programmar.PLI.Terminals;

import com.eagle.tokens.TerminalLevelToken;

public class PLI_Level extends TerminalLevelToken
{
	@Override
	protected boolean validateLevel()
	{
		// Passed all the tests!
		return true;
	}
	
	@Override
	public String description()
	{
		return "PL/I level number.";
	}
}
