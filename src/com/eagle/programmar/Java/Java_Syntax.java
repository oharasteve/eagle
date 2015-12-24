// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Java;

import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.Java.Terminals.Java_Comment;

public class Java_Syntax extends EagleSyntax
{
	public static boolean isCaseSensitive = true;
	
	@Override
	public String syntaxId()
	{
		return "Java";
	}
	
	public Java_Syntax()
	{
		_isCaseSensitive = isCaseSensitive;
		_continuationChar = null;
		_extraCharacters = "_";
		_commentInstance = new Java_Comment();
		_punctuationExceptions = new String[] { "//", "/*", "!=", "<=", "==", ">=", "...", "++", "--", "||", "&&" };
		
		addReservedWords(keywords);
	}
	
	private String[] keywords = new String[] {
		"abstract",
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
		"do",
		"double",
		"else",
		"enum",
		"extends",
		"final",
		"finally",
		"float",
		"for",
		"goto",
		"if",
		"implements",
		"import",
		"instanceof",
		"int",
		"interface",
		"long",
		"native",
		"new",
		"null",
		"package",
		"private",
		"protected",
		"public",
		"return",
		"short",
		"static",
		"super",
		"switch",
		"synchronized",
		"this",
		"throw",
		"throws",
		"transient",
		"try",
		"void",
		"volatile",
		"while",
	};
}
