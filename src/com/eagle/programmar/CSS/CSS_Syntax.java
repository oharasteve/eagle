// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.CSS;

import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.CSS.Terminals.CSS_Comment;

public class CSS_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "CSS";
	}
	
	public CSS_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		_punctuationExceptions = new String[] { "::" };
		_commentInstance = new CSS_Comment();
		
		addReservedWords(keywords);
	}

	private String[] keywords = new String[] {
		"media",
		"-moz-document",
		"not",
		"rgb",
		"rgba",
		"rotate",
		"url-prefix"
	};
}
