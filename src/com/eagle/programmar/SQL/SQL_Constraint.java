// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Statements.SQL_CreateStatement.SQL_CreateTableStatement.SQL_MoreKeyFields;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Key_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class SQL_Constraint extends TokenSequence
{
	public SQL_Keyword CONSTRAINT = new SQL_Keyword("CONSTRAINT");
	public SQL_Key_Definition key;
	public SQL_KeywordChoice FOREIGN = new SQL_KeywordChoice("FOREIGN", "PRIMARY");
	public SQL_Keyword KEY = new SQL_Keyword("KEY");
	public SQL_Punctuation leftParen1 = new SQL_Punctuation('(');
	public SQL_Identifier_Reference keyField1;
	public @OPT TokenList<SQL_MoreKeyFields> moreFields1;
	public SQL_Punctuation rightParen1 = new SQL_Punctuation(')');
	public @OPT SQL_ConstraintReference references;
	
	public static class SQL_ConstraintReference extends TokenSequence
	{
		public SQL_Keyword REFERENCES = new SQL_Keyword("REFERENCES");
		public SQL_Identifier_Reference referenceField;
		public SQL_Punctuation leftParen2 = new SQL_Punctuation('(');
		public SQL_Identifier_Reference keyField2;
		public @OPT TokenList<SQL_MoreKeyFields> moreFields2;
		public SQL_Punctuation rightParen2 = new SQL_Punctuation(')');
	}
}
