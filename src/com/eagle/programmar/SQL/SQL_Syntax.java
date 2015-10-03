// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.SQL;

import com.eagle.programmar.EagleSyntax;

public class SQL_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "SQL";
	}
	
	public SQL_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		_punctuationExceptions = new String[] { "!=", "<=", "==", ">=", "=>", "||", "&&", "/*" };
		
		addReservedWords(keywords);
	}

	private String[] keywords = new String[]
	{
		"ALTER",
		"BEGIN",
		"COMMIT",
		"CONSTRAINT",
		"CREATE",
		"DECLARE",
		"DELETE",
		"END",
		"FROM",
		"INSERT",
		"INTO",
		//"KEY",
		"PRIMARY",
		"REM",
		"SELECT",
		"UNIQUE",
		"UPDATE",
		"WHERE",
		"WITH"
	};
}
