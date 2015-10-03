// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.PHP;

import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.HTML.HTML_Syntax;

public class PHP_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "PHP";
	}
	
	public PHP_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = HTML_Syntax.PUNCT;
	}
}
