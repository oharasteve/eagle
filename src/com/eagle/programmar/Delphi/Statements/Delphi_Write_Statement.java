// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Write_Statement extends TokenSequence
{
	public Delphi_Keyword WRITE = new Delphi_Keyword("Write");
	public PunctuationLeftParen leftParen;
	public Delphi_Literal literal;
	public PunctuationRightParen rightParen;
}
