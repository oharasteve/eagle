// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.tokens.tests;

import org.junit.Test;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.EagleSyntax;
import com.eagle.tokens.TerminalPunctuationChoice;
import com.eagle.tokens.TerminalPunctuationToken;

import junit.framework.TestCase;

public class TerminalPunctuationTests extends TestCase
{
	private static class TestPunctuation extends TerminalPunctuationToken
	{
		public TestPunctuation(String punct, String exceptions)
		{
			super(punct);
			
			this.setSyntax(new TestPunctuationSyntax(exceptions));
		}
	}
	
	private static class TestPunctuationChoice extends TerminalPunctuationChoice
	{
		public TestPunctuationChoice(String[] punct, String exceptions)
		{
			super(punct);
			
			this.setSyntax(new TestPunctuationSyntax(exceptions));
		}
	}
	
	private static class TestPunctuationSyntax extends EagleSyntax
	{
		public TestPunctuationSyntax(String exceptions)
		{
			_punctuationExceptions = exceptions.split(" ");
		}

		@Override
		public String syntaxId()
		{
			return "TestPunct";
		}
	}
	
	private static void verifyPunctuation(String line, String punctStr, String exceptions, boolean shouldMatch)
	{
		EagleFileReader lines = new EagleFileReader();
		lines.add(line);
		lines.setCurrentChar(1);	// Get past the identifier
		TestPunctuation punct = new TestPunctuation(punctStr, exceptions);
		boolean matched = punct.parse(lines);
		if (matched)
		{
			assertTrue("Should not have matched but did: " + line + "   " + punctStr + "   '" + exceptions + "'", shouldMatch);
		}
		else
		{
			assertFalse("Should have matched but did not: " + line + "   " + punctStr + "   '" + exceptions + "'", shouldMatch);
		}
	}
	
	private static void verifyPunctuationChoice(String line, String[] punctStr, String exceptions, boolean shouldMatch)
	{
		EagleFileReader lines = new EagleFileReader();
		lines.add(line);
		lines.setCurrentChar(1);	// Get past the identifier
		TerminalPunctuationChoice punct = new TestPunctuationChoice(punctStr, exceptions);
		boolean matched = punct.parse(lines);
		if (matched)
		{
			assertTrue("Should not have matched, but did: " + line + " " + punctStr + " " + exceptions, shouldMatch);
		}
		else
		{
			assertFalse("Should have matched, but did not: " + line + " " + punctStr + " " + exceptions, shouldMatch);
		}
	}
	
	@Test public void testSimplePunctuation()
	{
		verifyPunctuation("a=5", "=", "++ --", true);
		verifyPunctuation("b!=5", "!=", "++ -- != == **", true);
		verifyPunctuation("c!==5", "!==", "++ -- !== == **", true);
		verifyPunctuation("d!===5", "!===", "++ -- !== == **", true);
		verifyPunctuation("e!=5", "!", "++ -- !== == **", true);

		verifyPunctuation("A!=5", "!", "++ -- != == **", false);
		verifyPunctuation("B!==5", "!", "++ -- !== **", false);
		verifyPunctuation("C==5", "=", "++ -- == **", false);
		verifyPunctuation("D!==5", "!=", "++ -- !== == **", false);
		verifyPunctuation("E==5", "=", "++ == --", false);

		String[] puncts = new String[] { "+", "-", "*", "!", "/" };
		verifyPunctuationChoice("z!=5", puncts, "++ -- == **", true);
		verifyPunctuationChoice("Z!=5", puncts, "++ -- != == **", false);
	}
}
