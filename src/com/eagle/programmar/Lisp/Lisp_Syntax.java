// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp;

import java.lang.reflect.Field;

import com.eagle.programmar.EagleSyntax;

public class Lisp_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Lisp";
	}
	
	public Lisp_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = null;
		_extraCharacters = "_";
		
		addReservedWords(keywords);

		// Pick up all the Function names.
		for (Field fld : Lisp_Function.class.getDeclaredFields())
		{
			Class<?> statementClass = fld.getType();
			String name = statementClass.getDeclaredFields()[1].getName().toLowerCase();
			// Probably should verify that it is a Lisp_Keyword, and pick up its value, etc.
			addReservedWord(name);
		}
	}

	private String[] keywords = new String[] {
			"char",
			"do",
			"let",
			"nil",
			"prog",
			"string"
	};
}
