// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 1, 2015

package com.eagle.math;

public class BooleanValue extends EagleValue
{
	private boolean _boolValue;
	
	public BooleanValue(boolean b)
	{
		_boolValue = b;
	}
	
	@Override
	public boolean forceBooleanValue()
	{
		return _boolValue;
	}

	@Override
	public int forceIntegerValue()
	{
		return (_boolValue ? 1 : 0);
	}

	@Override
	public String forceStringValue()
	{
		return Boolean.toString(_boolValue);
	}
}
