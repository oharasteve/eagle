// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.C;

import com.eagle.programmar.EagleSyntax;

public class C_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "C";
	}
	
	public C_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = "\\";
		_extraCharacters = "_";
		_punctuationExceptions = new String[] { "!=", "<=", "==", ">=", "/*", "&&", "||", "..", "->", "++", "--", "::" };
		
		addReservedWords(keywords);
		addReservedWords(C_Program.getPrimitives());
		addReservedWords(C_Program.getModifiers());
	}
	
	private String[] keywords = new String[] {
		"break",
		"case",
		"continue",
		"const",
		"default",
		"do",
		"else",
		"extern",
		"float",
		"for",
		"if",
		"int",
		"NULL",
		"return",
		"sizeof",
		"struct",
		"switch",
		"typedef",
		"union",
		"volatile",
		"while"
	};
}
