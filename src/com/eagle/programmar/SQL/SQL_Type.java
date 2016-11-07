// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_Type extends TokenChooser
{
	public @CHOICE static class SQL_TypeSet extends TokenSequence
	{
		public SQL_Keyword SET = new SQL_Keyword("SET");
		public PunctuationLeftParen leftParen;
		public SeparatedList<SQL_Expression,PunctuationComma> setValues;
		public PunctuationRightParen rightParen;
	}

	public @CHOICE static class SQL_TypeVarChar extends TokenSequence
	{
		public SQL_KeywordChoice charType = new SQL_KeywordChoice
		(
			"CHAR",
			"LONGVARCHAR",
			"NVARCHAR2",
			"VARCHAR",
			"VARCHAR2"
		);
		public @OPT SQL_TypeSize size;
	}

	public @CHOICE static class SQL_TypeBoolean extends TokenSequence
	{
		public SQL_KeywordChoice BOOLEAN = new SQL_KeywordChoice("BOOLEAN");
	}
	
	public @CHOICE static class SQL_TypeBigInt extends TokenSequence
	{
		public SQL_Keyword BIGINT = new SQL_Keyword("BIGINT");
		public SQL_TypeSize size;
	}

	public @CHOICE static class SQL_TypeInt extends TokenSequence
	{
		public SQL_KeywordChoice INT = new SQL_KeywordChoice("INT", "INTEGER", "NUMBER");
		public @OPT SQL_TypeSize size;
	}

	public @CHOICE static class SQL_TypeDouble extends TokenSequence
	{
		public SQL_KeywordChoice DOUBLE = new SQL_KeywordChoice("DOUBLE");
	}
	
	public @CHOICE static class SQL_TypeRaw extends TokenSequence
	{
		public SQL_Keyword RAW = new SQL_Keyword("RAW");
		public SQL_TypeSize size;
	}

	public @CHOICE static class SQL_TypeBlob extends TokenSequence
	{
		public SQL_KeywordChoice BLOB = new SQL_KeywordChoice("BLOB", "NCLOB");
	}
	
	public @CHOICE static class SQL_TypeText extends TokenSequence
	{
		public SQL_Keyword TEXT = new SQL_Keyword("TEXT");
	}

	public @CHOICE static class SQL_TypeDate extends TokenSequence
	{
		public SQL_Keyword DATE = new SQL_Keyword("DATE");
	}

	public @CHOICE static class SQL_TypeTimeStamp extends TokenSequence
	{
		public SQL_Keyword TIMESTAMP = new SQL_Keyword("TIMESTAMP");
	}

	public @CHOICE static class SQL_TypeEnum extends TokenSequence
	{
		public SQL_Keyword ENUM = new SQL_Keyword("ENUM");
		public PunctuationLeftParen leftParen;
		public SeparatedList<SQL_Expression,PunctuationComma> enumVal;
		public PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class SQL_TypeSize extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public SQL_Number size;
		public @OPT PunctuationComma comma;
		public @OPT SQL_Number size2;
		public PunctuationRightParen rightParen;
	}
}
