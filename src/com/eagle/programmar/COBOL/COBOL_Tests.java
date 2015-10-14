// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 26, 2012

package com.eagle.programmar.COBOL;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.ParserManager;
import com.eagle.tests.EagleTests;

@RunWith(Parameterized.class)
public class COBOL_Tests extends EagleTests
{
	private COBOL_Interpreter _interpreter = new COBOL_Interpreter();

	private static ArrayList<Object[]> tests = new ArrayList<Object[]>();

	private String _testName;
	private String _data;
	private String _proc;
	private String _expected;

	// Figure out which COBOL Statements are actual statements, and hence should have test cases
	@Parameters public static Collection<Object[]> collectTestCases()
	{
		for (Field fld : COBOL_Statement.class.getDeclaredFields())
		{
			maybeAddTests(fld.getType());
		}
		
		for (Class<?> cls : COBOL_Expression.class.getDeclaredClasses())
		{
			maybeAddTests(cls);
		}
		
		return tests;
	}
	
	// Class by each Statement type to add a new test
	public static void addTestStmt(String testName, String data, String proc, String expected)
	{
		tests.add(new Object[] { testName, data, proc, expected } );
	}

	// Class by each Statement type to add a new test
	public static void addTestExpr(String testName, String expr, String expected)
	{
		addTestStmt(testName, null, "DISPLAY " + expr + ".", expected + "\r\n");
	}

	
	// JUnit creates one instance of this class, for each element returned by collectTestCases
	public COBOL_Tests(String testName, String data, String proc, String expected)
	{
		_testName = testName;
		_data = data;
		_proc = proc;
		_expected = expected;
	}

	// Then JUnit calls this test on it
	@Test public void testStatement()
	{
		// Build up a little program
		EagleFileReader pgm = new EagleFileReader();
		pgm.add("IDENTIFICATION DIVISION.");
		if (_data != null)
		{
			pgm.add("DATA DIVISION.");
			pgm.add("WORKING-STORAGE SECTION.");
			pgm.add(_data);
		}
		pgm.add("PROCEDURE DIVISION.");
		pgm.add(_proc);
		COBOL_Program_Complete lang = new COBOL_Program_Free_Format();
		ParserManager parser = new ParserManager();
		parser.parseLines(pgm, lang, lang);
		
		// Now, run the program
		PrintStream saveOut = System.out;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream prt = new PrintStream(stream);
		System.setOut(prt);	// Redirect into my byte stream
		
		_interpreter.execute(lang);
		
		System.setOut(saveOut);	// Restore original stdout
		prt.close();
		String actualOutput = stream.toString();
		assert _expected == actualOutput : "Values didn't match";
		System.out.print("==== " + _testName + " ====\n");
		if (_data != null) System.out.print(_data + "\n");
		System.out.print(_proc + "\n");
		System.out.println("Printed " + _expected + "As expected.\n");
	}
}
