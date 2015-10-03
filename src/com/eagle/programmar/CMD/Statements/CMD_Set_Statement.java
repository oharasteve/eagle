// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Symbols.CMD_Variable_Definition;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.programmar.CMD.Terminals.CMD_RestOfLine;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenChooser;

public class CMD_Set_Statement extends TokenSequence
{
	public @DOC("set.mspx") CMD_Keyword SET = new CMD_Keyword("set");
	public CMD_Set_What setWhat;
	
	public static class CMD_Set_What extends TokenChooser
	{
		public static class CMS_Set_Regular extends TokenSequence
		{
			public CMD_Variable_Definition var;
			public CMD_Punctuation equals = new CMD_Punctuation('=');
			public CMD_RestOfLine value;
		}
		
		public static class CMD_Set_Assigment extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Keyword A = new CMD_Keyword("a");
			public CMD_Variable_Definition var;
			public CMD_Punctuation equals = new CMD_Punctuation('=');
			public CMD_Expression expr;
		}
		
		public static class CMD_Set_Prompt extends TokenSequence
		{
			public CMD_Punctuation slash = new CMD_Punctuation('/');
			public CMD_Keyword P = new CMD_Keyword("p");
			public CMD_Variable_Definition var;
			public CMD_Punctuation equals = new CMD_Punctuation('=');
			public CMD_RestOfLine value;
		}
	}
}