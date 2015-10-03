// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 14, 2014

package com.eagle.tokens;

public abstract class TerminalOctalNumberToken extends TerminalNumberToken
{
	protected static final String OCTAL = "01234567";

	@Override
	public String showString()
	{
		return "Octal Number";
	}

	@Override
	public String description()
	{
		return "A octal number";
	}
}
