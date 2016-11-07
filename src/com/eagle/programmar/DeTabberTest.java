// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Convert tabs to spaces
 */

public class DeTabberTest extends TestCase
{
	@Test
	public void test1()
	{
		assertEquals(null, DeTabber.deTab(null));
		assertEquals("", DeTabber.deTab(""));
		assertEquals("abc", DeTabber.deTab("abc"));
	}
	
	@Test
	public void test2()
	{
		assertEquals("a       bc      d", DeTabber.deTab("a\tbc\td", 8));
	}
	
	@Test
	public void test3()
	{
		assertEquals("        abcdefg hi  ", DeTabber.deTab("\t\tabcdefg\thi\t"));
	}
}
