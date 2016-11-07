// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.tokens.tests;

import org.junit.Test;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalHexNumberToken;

import junit.framework.TestCase;

public class TerminalHexNumberTests extends TestCase
{
	private static class TestHex extends TerminalHexNumberToken
	{
		public String _hexPrefix;
		public String _suffixChars;
		
		public TestHex(String hexPrefix, String suffixChars)
		{
			_hexPrefix = hexPrefix;
			_suffixChars = suffixChars;
		}
		
		@Override
		public boolean parse(EagleFileReader lines)
		{
			return genericHex(lines, _hexPrefix, _suffixChars);
		}
	}
	
	@Test public void testHex1()
	{
		TestHex test = new TestHex("0x", "L");
		EagleFileReader lines = new EagleFileReader();
		lines.add("0x17Afec $^$#");
		assertTrue(test.parse(lines));
		assertEquals(test.getValue(), "0x17Afec");
	}
	
	@Test public void testHex2()
	{
		TestHex test = new TestHex("0x", "L");
		EagleFileReader lines = new EagleFileReader();
		lines.add("0x17AfecG");
		assertFalse(test.parse(lines));
	}
	
	@Test public void testHex3()
	{
		TestHex test = new TestHex("0x", null);
		EagleFileReader lines = new EagleFileReader();
		lines.add("-0x17Afec");
		assertTrue(test.parse(lines));
		assertEquals(test.getValue(), "-0x17Afec");
	}
}
