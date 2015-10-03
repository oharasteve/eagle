// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django;

import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.HTML.HTML_Syntax;

public class Django_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Django";
	}
	
	public Django_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		_punctuationExceptions = HTML_Syntax.PUNCT;
	}
}
