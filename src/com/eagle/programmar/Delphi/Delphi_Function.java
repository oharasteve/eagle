// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Delphi_Program.Delphi_Header;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Function extends TokenSequence
{
	public Delphi_FunctionForward forward;
	public @OPT TokenList<Delphi_Header> headers;
	public Delphi_BeginEnd body;
	public @OPT TokenList<Delphi_Comment> comments;
	public PunctuationSemicolon semicolon2;
	
	public static class Delphi_FunctionForward extends TokenSequence
	{
		public Delphi_Keyword FUNCTION = new Delphi_Keyword("Function");
		public Delphi_Variable name;
		public @OPT Delphi_Arguments args;
		public PunctuationColon colon;
		public Delphi_Type type;
		public PunctuationSemicolon semicolon1;
	}
}
