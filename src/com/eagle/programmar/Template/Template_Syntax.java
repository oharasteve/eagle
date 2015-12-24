// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template;

import com.eagle.programmar.EagleSyntax;

public class Template_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Template";
	}
	
	public Template_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] { "<=", ">=", "==", "!=" };
		
		addReservedWords(reservedWords);
	}
	
	private String[] reservedWords = new String[] {
		"data",
		"print",
	};
}