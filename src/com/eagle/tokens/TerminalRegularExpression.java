// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 1, 2014

package com.eagle.tokens;

public abstract class TerminalRegularExpression extends TerminalToken
{
	private String _expr;
	
	@Override
	public String showString()
	{
		return "Regular Expression";
	}

	@Override
	public String description()
	{
		return "A regular expression";
	}
	
	@Override
	public String toString()
	{
		return _expr;
	}
	
	@Override
	public void setValue(String val)
	{
		_expr = val;
		_present = (val != null);
	}

	@Override
	public String getDisplayStyleName()
	{
		return "regex";
	}
}