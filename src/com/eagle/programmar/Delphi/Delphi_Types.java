// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Type_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Types extends TokenSequence
{
	public Delphi_Keyword TYPE = new Delphi_Keyword("Type");
	public @OPT TokenList<Delphi_MoreTypes> moreTypes;
	
	public static class Delphi_MoreTypes extends TokenSequence
	{
		public Delphi_Type_Definition name;
		public PunctuationEquals equals;
		public Delphi_Type type;
		public PunctuationSemicolon semicolon;
	}
}
