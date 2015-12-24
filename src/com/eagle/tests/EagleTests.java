// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;

import org.junit.Assert;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.ParserManager;
import com.eagle.programmar.EagleLanguage;

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
	
	protected void runTest(EagleInterpreter interpreter, String testName, String[] input, String expected, EagleLanguage lang)
	{
		ParserManager parser = new ParserManager();
		parser.parseLines(new EagleFileReader(input), lang, lang);
		
		// Now, run the program
		PrintStream saveOut = System.out;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream prt = new PrintStream(stream);
		System.setOut(prt);	// Redirect into my byte stream
	
		if (! (lang instanceof EagleRunnable))
		{
			throw new RuntimeException("Need to implement EagleRunnable in " + lang.getName());
		}
		EagleRunnable runnable = (EagleRunnable) lang;
		runnable.interpret(interpreter);
		
		System.setOut(saveOut);	// Restore original stdout
		prt.close();
		String actualOutput = stream.toString().replaceAll("\\r", "");
		Assert.assertEquals(expected, actualOutput);
		System.out.println("==== " + testName + " ====");
		for (String s : input) System.out.println(s);
		System.out.println("Printed " + expected + "As expected.\n");
	}
}
