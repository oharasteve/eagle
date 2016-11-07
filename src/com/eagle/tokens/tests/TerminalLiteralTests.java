// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.tokens.tests;

import org.junit.Test;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalLiteralToken;

import junit.framework.TestCase;

public class TerminalLiteralTests extends TestCase
{
	private static class TestLiteral extends TerminalLiteralToken
	{
		private String _quotes;
		private boolean _hasEscape;
		private char _escape;
		private boolean _allowDoubled;
		private boolean _allowMultiline;
		
		public TestLiteral(String quotes, boolean hasEscape, char escape,
			boolean allowDoubled, boolean allowMultiline)
		{
			_quotes = quotes;
			_hasEscape = hasEscape;
			_escape = escape;
			_allowDoubled = allowDoubled;
			_allowMultiline = allowMultiline;
		}
		
		@Override
		public boolean parse(EagleFileReader lines)
		{
			return genericLiteral(lines, _quotes, _hasEscape, _escape, _allowDoubled, _allowMultiline);
		}
	}
	
	private static void verifyLiteral(String literal, String expected, String quotes, boolean hasEscape, char escape,
			boolean allowDoubled)
	{
		EagleFileReader lines = new EagleFileReader();
		lines.add(literal);
		TestLiteral testLit = new TestLiteral(quotes, hasEscape, escape, allowDoubled, false);
		assertEquals(testLit.parse(lines), true);
		assertEquals(expected, testLit.getValue());
	}
	
	@Test public void testSimpleLiterals()
	{
		verifyLiteral("'Kelly O''Brien'", "'Kelly O''Brien'", "'", false, '?', true);
		verifyLiteral("'Kelly O''Brien'", "'Kelly O'", "'", false, '?', false);
		verifyLiteral("'Kelly O\\'Brien'", "'Kelly O\\'Brien'", "'", true, '\\', false);
		verifyLiteral("'Kelly O\\'Brien'", "'Kelly O\\'", "'", true, '?', false);
	}
	
	@Test public void testMultilineLiterals()
	{
		EagleFileReader lines = new EagleFileReader();
		lines.add("'Once upon a time");
		lines.add("there was a farmer");
		lines.add("who lived in the hinterlands' 34");
		
		TestLiteral testLit = new TestLiteral("'", false, '?', false, true);
		assertEquals(testLit.parse(lines), true);
		assertEquals("'Once upon a time\nthere was a farmer\nwho lived in the hinterlands'", testLit.getValue());
	}
	
	@Test public void testBlankMultilineLiterals()
	{
		EagleFileReader lines = new EagleFileReader();
		lines.add("\"Once upon a time");
		lines.add("there was a farmer");
		lines.add("");
		lines.add("");
		lines.add("who lived in the hinterlands\" 34");
		
		TestLiteral testLit = new TestLiteral("\"", false, '?', false, true);
		assertEquals(testLit.parse(lines), true);
		assertEquals("\"Once upon a time\nthere was a farmer\n\n\nwho lived in the hinterlands\"", testLit.getValue());
	}
}
