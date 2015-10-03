// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Vars extends TokenSequence
{
	public Delphi_Keyword VAR = new Delphi_Keyword("Var");
	public TokenList<Delphi_Var> vars;
	
	public static class Delphi_Var extends TokenSequence
	{
		public Delphi_Variable_Definition var;
		public @OPT TokenList<Delphi_MoreVars> moreVars;
		public Delphi_Punctuation equals = new Delphi_Punctuation(':');
		public Delphi_Type type;
		public Delphi_Punctuation semicolon = new Delphi_Punctuation(';');
		public @OPT TokenList<Delphi_Comment> comments;
		
		public static class Delphi_MoreVars extends TokenSequence
		{
			public Delphi_Punctuation comma = new Delphi_Punctuation(',');
			public Delphi_Variable_Definition var;
		}
	}
}
