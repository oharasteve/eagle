// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CMacro.Terminals.CMacro_Identifier;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_Include_Statement extends TokenSequence
{
	public @DOC("Include-Syntax.html") C_Keyword INCLUDE = new C_Keyword("include");
	public CMacro_IncludeWhat what;
	
	public static class CMacro_IncludeWhat extends TokenChooser
	{
		public C_Literal filename;
		
		public static class CMacro_IncludeBuiltin extends TokenSequence
		{
			public C_Punctuation lessThen = new C_Punctuation('<');
			public @OPT CMacro_Include_Sys sys;
			public CMacro_Identifier file;
			public @OPT TokenList<CMacro_IncludeDotMore> more;
			public C_Punctuation greaterThan = new C_Punctuation('>');
			
			public static class CMacro_Include_Sys extends TokenSequence
			{
				public CMacro_Identifier library;
				public C_Punctuation slash = new C_Punctuation('/');
			}
			
			public static class CMacro_IncludeDotMore extends TokenSequence
			{
				public C_Punctuation dot = new C_Punctuation('.');
				public CMacro_Identifier file;
			}
		}
	}
}