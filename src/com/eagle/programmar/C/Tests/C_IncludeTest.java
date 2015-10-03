// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2015

package com.eagle.programmar.C.Tests;

import junit.framework.TestCase;

import org.junit.Test;

import com.eagle.preprocess.FindIncludeFile;
import com.eagle.programmar.C.C_Include;

public class C_IncludeTest extends TestCase implements FindIncludeFile
{
	private String[] mainFile = new String[] {
			"#include <stdio.h>",
			"#ifndef A_H",
			"#include \"a.h\"",
			"#endif",
			"#ifndef C_H",
			"#include \"c.h\"",
			"#endif",
			"#ifdef JUNK",
			"#include \"d.h\"",
			"#endif",
			"",
			"struct a {",
			"  A_MACRO",
			"  int temp;",
			"  B_MACRO",
			"}",
	};
	
	private String[] fileA = new String[] {
			"#ifndef B_H",
			"#include \"b.h\"",
			"#endif",
			"#define A_MACRO int a;",
	};
	
	private String[] fileB = new String[] {
			"#ifndef A_H",
			"#include \"a.h\"",
			"#endif",
			"#ifndef C_H",
			"#include \"c.h\"",
			"#endif",
			"#define B_MACRO int b;",
	};
	
	private String[] fileC = new String[] {
			"#ifndef C_H",
			"#define C_H",
			"#ifndef D_H",
			"#include \"d.h\"",
			"#endif",
			"#define C_MACRO int c;",
			"#endif",
	};
	
	private String[] fileD = new String[] {
			"#ifndef D_H",
			"#define D_H",
			"#ifndef C_H",
			"#include \"c.h\"",
			"#endif",
			"#define D_MACRO int d;",
			"#endif",
	};
	
	private String[] expected = new String[] {
			"#include <stdio.h>",
			"#define D_MACRO int d;",
			"#define C_MACRO int c;",
			"#define B_MACRO int b;",
			"#define A_MACRO int a;",
			"",
			"struct a {",
			"  int d;",
			"  int temp;",
			"  int b;",
			"}",
	};

	@Test
	public void testIncludes()
	{
		// Create the C include processor
		C_Include cInclude = new C_Include();
		cInclude.preprocess(mainFile);
		
		assertEquals("Length is wrong", expected.length, mainFile.length);
		for (int i = 0; i < mainFile.length; i++)
		{
			assertEquals("Line " + (i+1) + " differs:\n" + mainFile[i] + "\n" + expected[i], mainFile[i]);
		}
	}

	@Override
	public String[] findFile(String dir, String fname)
	{
		if (fname.equals("a.h")) return fileA;
		if (fname.equals("b.h")) return fileB;
		if (fname.equals("c.h")) return fileC;
		if (fname.equals("d.h")) return fileD;
		return null;
	}
}
