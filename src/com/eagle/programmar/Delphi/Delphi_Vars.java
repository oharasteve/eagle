// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Vars extends TokenSequence
{
	public Delphi_Keyword VAR = new Delphi_Keyword("Var");
	public TokenList<Delphi_Var> vars;
	
	public static class Delphi_Var extends TokenSequence
	{
		public SeparatedList<Delphi_Variable_Definition,PunctuationComma> vars;
		public PunctuationColon colon;
		public Delphi_Type type;
		public PunctuationSemicolon semicolon;
		public @OPT TokenList<Delphi_Comment> comments;
	}
}
