// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 1, 2015

package com.eagle.math;

public class StringValue extends EagleValue
{
	private String _strValue = null;
	
	public StringValue(String s)
	{
		_strValue = s;
	}

	@Override
	public boolean forceBooleanValue()
	{
		if (_strValue == null || _strValue.length() == 0) return false;
		String s = _strValue.trim();
		return s.equalsIgnoreCase("true");
	}

	@Override
	public int forceIntegerValue()
	{
		if (_strValue == null || _strValue.length() == 0) return 0;
		return Integer.parseInt(_strValue);
	}

	@Override
	public String forceStringValue()
	{
		return _strValue;
	}
}
