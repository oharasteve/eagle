// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Uses extends TokenSequence
{
	public Delphi_Keyword USES = new Delphi_Keyword("Uses");
	public SeparatedList<Delphi_Identifier_Reference,PunctuationComma> uses;
	public PunctuationSemicolon semicolon;
}
