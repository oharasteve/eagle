// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Parameter_List extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public @OPT Delphi_Keyword INHERITED = new Delphi_Keyword("Inherited");
	public @OPT SeparatedList<Delphi_Expression,PunctuationComma> exprs;
	public PunctuationRightParen rightParen;
}
