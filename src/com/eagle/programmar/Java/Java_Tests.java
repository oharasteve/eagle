// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2013

package com.eagle.programmar.Java;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.ParserManager;
import com.eagle.tests.EagleTests;

@RunWith(Parameterized.class)
public class Java_Tests extends EagleTests
{
	private Java_Interpreter _interpreter = new Java_Interpreter();

	private static ArrayList<Object[]> tests = new ArrayList<Object[]>();

	private String _testName;
	private String _stmt;
	private String _expected;

	@Parameters(name="{index}: {0}")
	public static Collection<Object[]> collectTestCases()
	{
		// Figure out which Java Statements are actual statements, and hence should have test cases
		for (Field fld : Java_Statement.class.getDeclaredFields())
		{
			maybeAddTests(fld.getType());
		}

		// Figure out which Java Expressions have test cases
		for (Class<?> cls : Java_Expression.class.getDeclaredClasses())
		{
			maybeAddTests(cls);
		}
		
		return tests;
	}
	
	// Called by each Java Statement to add a new test
	public static void addTestStmt(String testName, String stmt, String expected)
	{
		tests.add(new Object[] { testName, stmt, expected } );
	}
	
	// Called by each Java Expression to add a new test
	public static void addTestExpr(String testName, String expr, String expected)
	{
		addTestStmt(testName, "System.out.println(" + expr + ");", expected + "\n");
	}
	
	// JUnit creates one instance of this class, for each element returned by collectTestCases
	public Java_Tests(String testName, String stmt, String expected)
	{
		_testName = testName;
		_stmt = stmt;
		_expected = expected;
	}

	// Then JUnit calls this test on it
	@Override
	@Test public void runTest() throws Exception
	{
		// Build up a little program
		EagleFileReader pgm = new EagleFileReader();
		pgm.add("class test {");
		pgm.add("  int six = 6;");
		pgm.add("  int five = 5;");
		pgm.add(_stmt);
		pgm.add("}");
		Java_Program lang = new Java_Program();
		ParserManager parser = new ParserManager();
		parser.parseLines(pgm, lang);
		
		// Now, run the program
		PrintStream saveOut = System.out;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream prt = new PrintStream(stream);
		System.setOut(prt);	// Redirect into my byte stream
		
		lang.interpret(_interpreter);
		
		System.setOut(saveOut);	// Restore original stdout
		prt.close();
		String actualOutput = stream.toString().replaceAll("\\r", "");
		Assert.assertEquals(_expected, actualOutput);
		System.out.println("==== " + _testName + " ====\n" + _stmt + "\nPrinted " + _expected + "As expected.\n");
	}
}
