// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Delphi_Program.Delphi_Header;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Function extends TokenSequence
{
	public Delphi_FunctionForward forward;
	public @OPT TokenList<Delphi_Header> headers;
	public Delphi_BeginEnd body;
	public @OPT TokenList<Delphi_Comment> comments;
	public Delphi_Punctuation semicolon2 = new Delphi_Punctuation(';');
	
	public static class Delphi_FunctionForward extends TokenSequence
	{
		public Delphi_Keyword FUNCTION = new Delphi_Keyword("Function");
		public Delphi_Variable name;
		public @OPT Delphi_Arguments args;
		public Delphi_Punctuation colon = new Delphi_Punctuation(':');
		public Delphi_Type type;
		public Delphi_Punctuation semicolon1 = new Delphi_Punctuation(';');
	}
}
