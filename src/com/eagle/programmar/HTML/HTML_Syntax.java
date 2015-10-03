// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.HTML;

import com.eagle.programmar.EagleSyntax;

public class HTML_Syntax extends EagleSyntax
{
	public static final String[] PUNCT = new String [] {
		"<!", "<!--",
		"</", "/>",
		"{%", "%}",
		"<?", "?>" };
	
	@Override
	public String syntaxId()
	{
		return "HTML";
	}
	
	public HTML_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		_punctuationExceptions = PUNCT;
		
		addReservedWords(keywords);
	}

	private String[] keywords = new String[] {
		"a",
		"caption",
		"pre",
		"script",
		"style",
		"table",
		"td",
		"tr"
	};
}
