// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Delphi_Program.Delphi_Header;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Procedure extends TokenSequence
{
	public Delphi_ProcedureForward forward;
	public @OPT TokenList<Delphi_Header> headers;
	public @OPT Delphi_BeginEnd body;
	public @OPT TokenList<Delphi_Comment> comments;
	public @OPT PunctuationSemicolon semicolon;
	
	public static class Delphi_ProcedureForward extends TokenSequence
	{
		public Delphi_KeywordChoice PROCEDURE = new Delphi_KeywordChoice("Procedure", "Constructor", "Destructor");
		public Delphi_Variable name;
		public @OPT Delphi_Arguments args;
		public PunctuationSemicolon semicolon;
		public @OPT Delphi_Override override;

		public static class Delphi_Override extends TokenSequence
		{
			public Delphi_Keyword OVERRIDE = new Delphi_Keyword("Override");
			public PunctuationSemicolon semicolon;
		}
	}
}
