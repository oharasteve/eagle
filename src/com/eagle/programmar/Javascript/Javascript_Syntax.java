// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Javascript;

import com.eagle.programmar.EagleSyntax;

public class Javascript_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Javascript";
	}
	
	public Javascript_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] { "/*", "!=", "<=", "==", ">=", "//", "&&", "||", "===", "!==", "!===" };
		
		addReservedWords(keywords);
	}
	
	private String[] keywords = new String[] {
		"abstract",
		"break",
		"case",
		"catch",
		"class",
		"const",
		"continue",
		"default",
		"delete",
		"do",
		"else",
		"enum",
		"extends",
		"final",
		"finally",
		"for",
		"function",
		"goto",
		"if",
		"implements",
		"import",
		"in",
		"instanceof",
		"interface",
		"native",
		"new",
		"null",
		"package",
		"private",
		"protected",
		"public",
		"return",
		"static",
		"super",
		"switch",
		"synchronized",
		"this",
		"throw",
		"throws",
		"transient",
		"try",
		"typeof",
		"var",
		"void",
		"volatile",
		"while"
	};
}
