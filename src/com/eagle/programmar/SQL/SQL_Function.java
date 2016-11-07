// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_Function extends TokenSequence
{
	public SQL_Variable funcName;
	public PunctuationLeftParen leftParen;
	public @OPT SeparatedList<SQL_FunctionParam,PunctuationComma> params;
	public PunctuationRightParen rightParen;
	
	public static class SQL_FunctionParam extends TokenChooser
	{
		public @CHOICE SQL_Expression arg;
		
		public @CHOICE static class SQL_FunctionColonParam extends TokenSequence
		{
			public PunctuationColon colon;
			public SQL_Expression arg;
		}
		
		public @FIRST static class SQL_FunctionNamedParam extends TokenSequence
		{
			public SQL_Identifier_Reference parameterName;
			public SQL_Punctuation equalsGreater = new SQL_Punctuation("=>");
			public SQL_Expression arg;
		}
	}
	
	public static class SQL_FunctionName extends TokenChooser
	{
		public @FIRST SQL_KeywordChoice fnName = new SQL_KeywordChoice(
				"CONCAT",
				"COUNT",
				"LENGTH",
				"MIN", 
				"SUBSTRING",
				"SYS_EXTRACT_UTC",
				"SYS_GUID"
		);
		
		public @CHOICE static class SQL_FunctionSCHEDULER extends TokenSequence
		{
			public SQL_Keyword DBMSSCHEDULER = new SQL_Keyword("DBMS_SCHEDULER");
			public PunctuationPeriod dot;
			public SQL_KeywordChoice DROPJOB = new SQL_KeywordChoice("CREATE_JOB", "DROP_JOB");
		}
		
		public @CHOICE static class SQL_FunctionLOB extends TokenSequence
		{
			public SQL_Keyword DBMSLOB = new SQL_Keyword("DBMS_LOB");
			public PunctuationPeriod dot;
			public SQL_Keyword GETLENGTH = new SQL_Keyword("GETLENGTH");
		}
		
		public @CHOICE static class SQL_FunctionJOB extends TokenSequence
		{
			public SQL_Keyword DBMSJOB = new SQL_Keyword("DBMS_JOB");
			public PunctuationPeriod dot;
			public SQL_Keyword REMOVE = new SQL_Keyword("REMOVE");
		}
	}
}