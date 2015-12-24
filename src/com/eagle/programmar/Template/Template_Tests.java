// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template;

import org.junit.Test;

import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.tests.EagleTests;

public class Template_Tests extends EagleTests
{
	private EagleSymbolTable _symbolTable = new EagleSymbolTable();
	private Template_Interpreter _interpreter = new Template_Interpreter(_symbolTable);

	@Test
	public void testSimple() throws Exception
	{
		String[] input = new String[] {
				"data n = 75;",
				"n = n + 10;",
				"print n + 15;",
		};
		Template_Program lang = new Template_Program();
		super.runTest(_interpreter, "testSimple", input, "100\n", lang);
	}
}
