// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.CSharp;

import com.eagle.programmar.EagleSyntax;

public class CSharp_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "CSharp";
	}
	
	public CSharp_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] { "!=", "<=", "==", ">=", "//", "..." };
		
		addReservedWords(keywords);
	}
	
	private String[] keywords = new String[] {
		"abstract",
		"as",
		"base",
		"bool",
		"boolean",
		"break",
		"byte",
		"case",
		"catch",
		"char",
		"class",
		"const",
		"continue",
		"default",
		"delegate",
		"do",
		"double",
		"else",
		"endregion",
		"enum",
		"event",
		"extern",
		"false",
		"final",
		"finally",
		"float",
		"for",
		"get",
		"goto",
		"if",
		"is",
		"import",
		"instanceof",
		"int",
		"interface",
		"lock",
		"long",
		"native",
		"new",
		"null",
		"out",
		"package",
		"partial",
		"private",
		"protected",
		"public",
		"readonly",
		"ref",
		"return",
		"sealed",
		"set",
		"short",
		"static",
		"string",
		"super",
		"switch",
		"this",
		"throw",
		"throws",
		"transient",
		"true",
		"try",
		"typeof",
		"using",
		"void",
		"volatile",
		"while"
	};
}
