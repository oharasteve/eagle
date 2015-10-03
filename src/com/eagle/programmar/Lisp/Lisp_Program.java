// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tokens.TokenList;

public class Lisp_Program extends EagleLanguage
{
	public static final String NAME = "Lisp";
	
	public Lisp_Program()
	{
		super(NAME, new Lisp_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.lispworks.com/documentation/HyperSpec/Body/";
	}
	
	public TokenList<Lisp_SExpr> sexprs;
}
