// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.SQL.Terminals.SQL_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class SQL_Program extends EagleLanguage
{
	public static final String NAME = "SQL";
	
	public SQL_Program()
	{
		super(NAME, new SQL_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/sql/";
	}
	
	public TokenList<SQL_StatementOrComment> statements;
	
	public static class SQL_StatementOrComment extends TokenChooser
	{
		public @CHOICE SQL_Statement statement;
		public @CHOICE SQL_Comment comment;
	}
}	
