// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Uses extends TokenSequence
{
	public Delphi_Keyword USES = new Delphi_Keyword("Uses");
	public Delphi_Identifier_Reference use;
	public @OPT TokenList<Delphi_MoreUses> moreUses;
	public Delphi_Punctuation semicolon = new Delphi_Punctuation(';');
	
	public static class Delphi_MoreUses extends TokenSequence
	{
		public Delphi_Punctuation comma = new Delphi_Punctuation(',');
		public Delphi_Identifier_Reference use;
	}
}
