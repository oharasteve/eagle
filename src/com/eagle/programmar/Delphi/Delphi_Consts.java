// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Consts extends TokenSequence
{
	public Delphi_Keyword CONST = new Delphi_Keyword("Const");
	public TokenList<Delphi_Const> constants;
	
	public static class Delphi_Const extends TokenSequence
	{
		public Delphi_Variable_Definition constant;
		public @OPT Delphi_ConstType type;
		public Delphi_Punctuation equals = new Delphi_Punctuation('=');
		public Delphi_Expression expr;
		public Delphi_Punctuation semicolon = new Delphi_Punctuation(';');
		public @OPT TokenList<Delphi_Comment> comments;
		
		public static class Delphi_ConstType extends TokenSequence
		{
			public Delphi_Punctuation colon = new Delphi_Punctuation(':');
			public Delphi_Type type;
		}
	}
}
