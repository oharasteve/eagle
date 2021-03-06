// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_GrantStatement extends TokenSequence
{
	public SQL_Keyword GRANT = new SQL_Keyword("GRANT");
	public SQL_GrantPermission permission;
	public SQL_Keyword TO = new SQL_Keyword("TO");
	public SQL_Identifier_Reference role;
	public PunctuationSemicolon semicolon;
	
	public static class SQL_GrantPermission extends TokenChooser
	{
		public @CHOICE SQL_Identifier_Reference permission;
		
		public @CHOICE static class SQL_GrantPermissionOn extends TokenSequence
		{
			public SQL_KeywordChoice EXECUTE = new SQL_KeywordChoice("EXECUTE", "SELECT");
			public SQL_Keyword ON = new SQL_Keyword("ON");
			public SQL_Identifier_Reference proc;
		}
	}
}
