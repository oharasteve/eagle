// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2011

package com.eagle.tokens;


public abstract class TerminalPictureToken extends TerminalToken
{
	protected String _pic;

	@Override
	public String getDisplayStyleName()
	{
		return "picture";
	}

	@Override
	public String toString()
	{
		return _pic;
	}
	
	@Override
	public void setValue(String val)
	{
		_pic = val;
	}

	@Override
	public String showString()
	{
		return "Picture";
	}
}
