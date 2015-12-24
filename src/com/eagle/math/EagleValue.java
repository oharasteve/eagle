// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 1, 2015

package com.eagle.math;

public abstract class EagleValue
{
	public abstract boolean forceBooleanValue();
	public abstract int forceIntegerValue();
	public abstract String forceStringValue();

	@Override
	public String toString()
	{
		return forceStringValue();
	}
}
