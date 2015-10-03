// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Perl;

import com.eagle.programmar.EagleSyntax;

public class Perl_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Perl";
	}
	
	public Perl_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] { "::", "==", "->", "<=", ">=", "<<", ">>", "!=", "=>", "=~" };
		
		addReservedWords(keywords);
	}

	private String[] keywords = new String[] {
		"abstract",
		"and",
		"clone",
		"const",
		"die",
		"do",
		"each",
		"else",
		"elseif",
		"function",
		"if",
		"include",
		"list",
		"my",
		"namespace",
		"new",
		"or",
		"private",
		"protected",
		"public",
		"throw",
		"use",
		"var",
		"while"
	};
}
