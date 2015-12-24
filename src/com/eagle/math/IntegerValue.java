// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 1, 2015

package com.eagle.math;

public class IntegerValue extends EagleValue
{
	private int _intValue;
	
	public IntegerValue(int i)
	{
		_intValue = i;
	}

	@Override
	public boolean forceBooleanValue()
	{
		return (_intValue == 0 ? false : true);
		
	}

	@Override
	public int forceIntegerValue()
	{
		return _intValue;
	}

	@Override
	public String forceStringValue()
	{
		return Integer.toString(_intValue);
	}
}
