// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Writeln_Statement extends TokenSequence
{
	public Delphi_Keyword WRITELN = new Delphi_Keyword("WriteLn");
	public @OPT Delphi_WriteLn_Something something;
	
	public static class Delphi_WriteLn_Something extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public SeparatedList<Delphi_Expression,PunctuationComma> exprs;
		public PunctuationRightParen rightParen;
	}
}
