// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class SQL_Function extends TokenSequence
{
	public SQL_Variable funcName;
	public SQL_Punctuation leftParen = new SQL_Punctuation('(');
	public @OPT SQL_FunctionParam param;
	public @OPT TokenList<SQL_ConcatMore> more;
	public SQL_Punctuation rightParen = new SQL_Punctuation(')');
	
	public static class SQL_FunctionParam extends TokenChooser
	{
		public SQL_Expression arg;
		
		public static class SQL_FunctionColonParam extends TokenSequence
		{
			public SQL_Punctuation colon = new SQL_Punctuation(':');
			public SQL_Expression arg;
		}
		
		public @FIRST static class SQL_FunctionNamedParam extends TokenSequence
		{
			public SQL_Identifier_Reference parameterName;
			public SQL_Punctuation equalsGreater = new SQL_Punctuation("=>");
			public SQL_Expression arg;
		}
	}
	
	public static class SQL_ConcatMore extends TokenSequence
	{
		public SQL_Punctuation comma = new SQL_Punctuation(',');
		public SQL_FunctionParam param;
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
		
		public static class SQL_FunctionSCHEDULER extends TokenSequence
		{
			public SQL_Keyword DBMSSCHEDULER = new SQL_Keyword("DBMS_SCHEDULER");
			public SQL_Punctuation dot = new SQL_Punctuation('.');
			public SQL_KeywordChoice DROPJOB = new SQL_KeywordChoice("CREATE_JOB", "DROP_JOB");
		}
		
		public static class SQL_FunctionLOB extends TokenSequence
		{
			public SQL_Keyword DBMSLOB = new SQL_Keyword("DBMS_LOB");
			public SQL_Punctuation dot = new SQL_Punctuation('.');
			public SQL_Keyword GETLENGTH = new SQL_Keyword("GETLENGTH");
		}
		
		public static class SQL_FunctionJOB extends TokenSequence
		{
			public SQL_Keyword DBMSJOB = new SQL_Keyword("DBMS_JOB");
			public SQL_Punctuation dot = new SQL_Punctuation('.');
			public SQL_Keyword REMOVE = new SQL_Keyword("REMOVE");
		}
	}
}