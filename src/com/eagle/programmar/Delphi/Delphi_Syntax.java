// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Delphi;

import com.eagle.programmar.EagleSyntax;

public class Delphi_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Delphi";
	}
	
	public Delphi_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		//_commentInstance = new Delphi_Comment();
		_punctuationExceptions = new String[] { "<>", "<=", ">=", ":=", ".." };
		
		addReservedWords(keywords);
	}
	
	private String[] keywords = new String[] {
		"array",
		"begin",
		"boolean",
		"class",
		"close",
		"const",
		"constructor",
		"else",
		"end",
		"except",
		"finally",
		"for",
		"function",
		"if",
		"implementation",
		"in",
		"inherited",
		"integer",
		"int64",
		"longint",
		"not",
		"private",
		"procedure",
		"property",
		"protected",
		"public",
		"raise",
		"record",
		"repeat",
		"then",
		"try",
		"type",
		"until",
		"uses",
		"var"
	};
}
