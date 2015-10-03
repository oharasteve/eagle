// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Writeln_Statement extends TokenSequence
{
	public Delphi_Keyword WRITELN = new Delphi_Keyword("WriteLn");
	public @OPT Delphi_WriteLn_Something something;
	
	public static class Delphi_WriteLn_Something extends TokenSequence
	{
		public Delphi_Punctuation leftParen = new Delphi_Punctuation('(');
		public Delphi_Expression expr;
		public @OPT TokenList<Delphi_Writeln_More> more;
		public Delphi_Punctuation rightParen = new Delphi_Punctuation(')');
		
		public static class Delphi_Writeln_More extends TokenSequence
		{
			public Delphi_Punctuation comma = new Delphi_Punctuation(',');
			public Delphi_Expression expr;
		}
	}
}
