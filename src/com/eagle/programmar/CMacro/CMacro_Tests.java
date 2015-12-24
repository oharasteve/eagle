// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 31, 2015

package com.eagle.programmar.CMacro;

import org.junit.Test;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.CMacro.Terminals.CMacro_RestOfLine;

import junit.framework.TestCase;

public class CMacro_Tests extends TestCase
{
	private static final String DEFINE = "#define ABC";
	
	@Test
	public void testSimpleMacro1()
	{
		String[] define = new String[] {
				DEFINE + " (56 + 75)// comment",
		};
		check(define, "(56 + 75)");
	}
	
	@Test
	public void testSimpleMacro2()
	{
		String[] define = new String[] {
				DEFINE + " (56 * 75)  /*ABC*/",
		};
		check(define, "(56 * 75)  ");
	}
	
	@Test
	public void testContinuedComment1()
	{
		String[] define = new String[] {
				DEFINE + " (56 * 75)  /*ABC",
				"DEF*/",
				"int i;",
		};
		check(define, "(56 * 75)  ");
	}
	
	@Test
	public void testMultilineMacro1()
	{
		String[] define = new String[] {
				DEFINE + " (1 + 2 + 3 \\",
				"   4 + 5 + 6 \\",
				"7 + 8 + 9)"
		};
		check(define, "(1 + 2 + 3 \n   4 + 5 + 6 \n7 + 8 + 9)");
	}
	
	@Test
	public void testMultilineMacro2()
	{
		String[] define = new String[] {
				DEFINE + " (1 + 2 + 3 \\",
				"   4 +/*COMMENT*/5 + 6 \\",
				"7 + 8 + 9)"
		};
		check(define, "(1 + 2 + 3 \n   4 +           5 + 6 \n7 + 8 + 9)");
	}

	@Test
	public void testEndsWithContinuedComment()
	{
		String[] define = new String[] {
				DEFINE + "    'u'     /* less than means the cell has a",
                         "             * value */"
		};
		check(define, "'u'");
	}
	
	private static void check(String[] inputLines, String expected)
	{
		CMacro_RestOfLine rest = new CMacro_RestOfLine();
		EagleFileReader lines = new EagleFileReader(inputLines);
		lines.setCurrentChar(DEFINE.length());
		lines.setCurrentLine(0);
		boolean ok = rest.parse(lines);
		assertTrue("Parse failed", ok);
		assertEquals(expected.trim(), rest.getValue().trim());
	}
}
