// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.math;

import com.eagle.tokens.AbstractToken;

public class TokenValue extends EagleValue
{
	private AbstractToken _token;
	
	public TokenValue(AbstractToken token)
	{
		_token = token;
	}

	@Override
	public boolean forceBooleanValue()
	{
		String s = forceStringValue();
		return s.equalsIgnoreCase("true");
	}

	@Override
	public int forceIntegerValue()
	{
		String s = forceStringValue();
		return Integer.parseInt(s);
	}

	@Override
	public String forceStringValue()
	{
		return _token.toString();
	}
	
	public AbstractToken getTokenValue()
	{
		return _token;
	}
}
