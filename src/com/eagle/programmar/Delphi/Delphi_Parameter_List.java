// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Parameter_List extends TokenSequence
{
	public Delphi_Punctuation leftParen = new Delphi_Punctuation('(');
	public @OPT Delphi_Keyword INHERITED = new Delphi_Keyword("Inherited");
	public @OPT Delphi_Expression expr;
	public @OPT TokenList<Delphi_MoreParams> moreParams;
	public Delphi_Punctuation rightParen = new Delphi_Punctuation(')');
	
	public static class Delphi_MoreParams extends TokenSequence
	{
		public Delphi_Punctuation comma = new Delphi_Punctuation(',');
		public Delphi_Expression expr;
	}
}
