// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

package com.eagle.tokens;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.eagle.programmar.EagleSyntax;


public class TokenChooser extends AbstractToken
{
	public static final String WHICH_TOKEN = "_whichToken";
	public @SKIP AbstractToken _whichToken = null;

	@Retention(RetentionPolicy.RUNTIME)
	public @interface FIRST
	{
		// @FIRST means look for this before the others
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface LAST
	{
		// @LAST means look for this after the others
	}
	
	@Override
	public void forceSyntax(EagleSyntax syntax) throws IllegalAccessException
	{
		setSyntax(syntax);
		if (_whichToken != null)
		{
			_whichToken.forceSyntax(syntax);
		}
	}
}
