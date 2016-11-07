// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.tokens.tests;

import org.junit.Test;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalOctalNumberToken;

import junit.framework.TestCase;

public class TerminalOctalNumberTests extends TestCase
{
	private static class TestOctal extends TerminalOctalNumberToken
	{
		public String _octalPrefix;
		public String _suffixChars;
		
		public TestOctal(String octalPrefix, String suffixChars)
		{
			_octalPrefix = octalPrefix;
			_suffixChars = suffixChars;
		}
		
		@Override
		public boolean parse(EagleFileReader lines)
		{
			return genericOctal(lines, _octalPrefix, _suffixChars);
		}
	}
	
	@Test public void testOctal1()
	{
		TestOctal test = new TestOctal("0o", "L");
		EagleFileReader lines = new EagleFileReader();
		lines.add("0o7123 $^$#");
		assertTrue(test.parse(lines));
		assertEquals(test.getValue(), "0o7123");
	}
	
	@Test public void testOctal2()
	{
		TestOctal test = new TestOctal("0o", "L");
		EagleFileReader lines = new EagleFileReader();
		lines.add("0x178");
		assertFalse(test.parse(lines));
	}
	
	@Test public void testOctal3()
	{
		TestOctal test = new TestOctal("0o", null);
		EagleFileReader lines = new EagleFileReader();
		lines.add("-0o1723");
		assertTrue(test.parse(lines));
		assertEquals(test.getValue(), "-0o1723");
	}
}
