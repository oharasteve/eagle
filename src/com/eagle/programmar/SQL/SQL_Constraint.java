// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Key_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_Constraint extends TokenSequence
{
	public SQL_Keyword CONSTRAINT = new SQL_Keyword("CONSTRAINT");
	public SQL_Key_Definition key;
	public SQL_KeywordChoice FOREIGN = new SQL_KeywordChoice("FOREIGN", "PRIMARY");
	public SQL_Keyword KEY = new SQL_Keyword("KEY");
	public PunctuationLeftParen leftParen1;
	public SeparatedList<SQL_Identifier_Reference,PunctuationComma> keyField1;
	public PunctuationRightParen rightParen1;
	public @OPT SQL_ConstraintReference references;
	
	public static class SQL_ConstraintReference extends TokenSequence
	{
		public SQL_Keyword REFERENCES = new SQL_Keyword("REFERENCES");
		public SQL_Identifier_Reference referenceField;
		public PunctuationLeftParen leftParen2;
		public SeparatedList<SQL_Identifier_Reference,PunctuationComma> keyField2;
		public PunctuationRightParen rightParen2;
	}
}
