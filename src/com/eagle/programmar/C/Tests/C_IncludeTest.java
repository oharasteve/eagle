// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2015

package com.eagle.programmar.C.Tests;

import org.junit.Test;

import com.eagle.preprocess.FindIncludeFile;
import com.eagle.programmar.C.C_Include;

import junit.framework.TestCase;

public class C_IncludeTest extends TestCase implements FindIncludeFile
{
	private String[] mainFile = new String[] {
			"#include <stdio.h>",	
			"#include \"a.h\"",
			"#ifndef C_H",
			"#include \"c.h\"",
			"#endif",
			"#ifdef JUNK",
			"#include \"d.h\"",
			"#endif",
			"",
			"struct a {",
			"  A_MACRO",
			"  B_MACRO",
			"  int temp;",
			"}",
	};
	
	private String[] fileA = new String[] {
			"#include \"b.h\"",
			"#define A_MACRO int a;",
	};
	
	private String[] fileB = new String[] {
			"#define B_MACRO int b;",
	};
	
	private String[] fileC = new String[] {
			"#ifndef C_H",
			"#define C_H",
			"#include \"d.h\"",
			"#define C_MACRO int c;",
			"#endif",
	};
	
	private String[] fileD = new String[] {
			"#ifndef D_H",
			"#define D_H",
			"#include \"c.h\"",
			"#define D_MACRO int d;",
			"#endif",
	};
	
	@Test
	public void testIncludes()
	{
		// Create the C include processor
		C_Include cInclude = new C_Include();
		cInclude.preprocess(mainFile);
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
