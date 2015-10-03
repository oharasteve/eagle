// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.tests;

import java.lang.reflect.Constructor;

import junit.framework.TestCase;

public abstract class EagleTests extends TestCase
{
	protected static void maybeAddTests(Class<?> cls)
	{
		if (EagleTestable.class.isAssignableFrom(cls))
		{
			try
			{
				Constructor<?> construct = cls.getConstructors()[0];
				EagleTestable testable = (EagleTestable) construct.newInstance();
				testable.addTests();
			}
			catch (Exception ex)
			{
				throw new RuntimeException("Unable to create a testable statement of type " + cls.getName());
			}
		}
	}
}
