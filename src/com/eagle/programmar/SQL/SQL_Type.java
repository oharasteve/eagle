// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class SQL_Type extends TokenChooser
{
	public static class SQL_TypeSet extends TokenSequence
	{
		public SQL_Keyword SET = new SQL_Keyword("SET");
		public SQL_Punctuation leftParen = new SQL_Punctuation('(');
		public SQL_Expression setValue;
		public @OPT TokenList<SQL_SetMoreValues> more;
		public SQL_Punctuation rightParen = new SQL_Punctuation(')');
		
		public static class SQL_SetMoreValues extends TokenSequence
		{
			public SQL_Punctuation comma = new SQL_Punctuation(',');
			public SQL_Expression setValue;
		}
	}

	public static class SQL_TypeVarChar extends TokenSequence
	{
		public SQL_KeywordChoice charType = new SQL_KeywordChoice
		(
			"CHAR",
			"LONGVARCHAR",
			"NVARCHAR2",
			"VARCHAR"
		);
		public @OPT SQL_TypeSize size;
	}

	public static class SQL_TypeBoolean extends TokenSequence
	{
		public SQL_KeywordChoice BOOLEAN = new SQL_KeywordChoice("BOOLEAN");
	}
	
	public static class SQL_TypeBigInt extends TokenSequence
	{
		public SQL_Keyword BIGINT = new SQL_Keyword("BIGINT");
		public SQL_TypeSize size;
	}

	public static class SQL_TypeInt extends TokenSequence
	{
		public SQL_KeywordChoice INT = new SQL_KeywordChoice("INT", "INTEGER", "NUMBER");
		public @OPT SQL_TypeSize size;
	}

	public static class SQL_TypeDouble extends TokenSequence
	{
		public SQL_KeywordChoice DOUBLE = new SQL_KeywordChoice("DOUBLE");
	}
	
	public static class SQL_TypeRaw extends TokenSequence
	{
		public SQL_Keyword RAW = new SQL_Keyword("RAW");
		public SQL_TypeSize size;
	}

	public static class SQL_TypeBlob extends TokenSequence
	{
		public SQL_KeywordChoice BLOB = new SQL_KeywordChoice("BLOB", "NCLOB");
	}
	
	public static class SQL_TypeText extends TokenSequence
	{
		public SQL_Keyword TEXT = new SQL_Keyword("TEXT");
	}

	public static class SQL_TypeDate extends TokenSequence
	{
		public SQL_Keyword DATE = new SQL_Keyword("DATE");
	}

	public static class SQL_TypeTimeStamp extends TokenSequence
	{
		public SQL_Keyword TIMESTAMP = new SQL_Keyword("TIMESTAMP");
	}

	public static class SQL_TypeEnum extends TokenSequence
	{
		public SQL_Keyword ENUM = new SQL_Keyword("ENUM");
		public SQL_Punctuation leftParen = new SQL_Punctuation('(');
		public SQL_Expression enumVal;
		public @OPT TokenList<SQL_SetMoreEnums> more;
		public SQL_Punctuation rightParen = new SQL_Punctuation(')');

		public static class SQL_SetMoreEnums extends TokenSequence
		{
			public SQL_Punctuation comma = new SQL_Punctuation(',');
			public SQL_Expression enumVal;
		}
	}
	
	public static class SQL_TypeSize extends TokenSequence
	{
		public SQL_Punctuation leftParen = new SQL_Punctuation('(');
		public SQL_Number size;
		public @OPT SQL_Punctuation comma = new SQL_Punctuation(',');
		public @OPT SQL_Number size2;
		public SQL_Punctuation rightParen = new SQL_Punctuation(')');
	}
}
