// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Consts extends TokenSequence
{
	public Delphi_Keyword CONST = new Delphi_Keyword("Const");
	public @OPT TokenList<Delphi_Comment> comments;
	public TokenList<Delphi_Const> constants;
	
	public static class Delphi_Const extends TokenSequence
	{
		public Delphi_Variable_Definition constant;
		public @OPT Delphi_ConstType type;
		public PunctuationEquals equals;
		public Delphi_Expression expr;
		public PunctuationSemicolon semicolon;
		public @OPT TokenList<Delphi_Comment> comments;
		
		public static class Delphi_ConstType extends TokenSequence
		{
			public PunctuationColon colon;
			public Delphi_Type type;
		}
	}
}
