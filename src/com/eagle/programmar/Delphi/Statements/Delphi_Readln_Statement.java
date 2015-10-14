// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Readln_Statement extends TokenSequence
{
	public Delphi_Keyword READLN = new Delphi_Keyword("ReadLn");
	public PunctuationLeftParen leftParen;
	public Delphi_Identifier_Reference var;
	public PunctuationRightParen rightParen;
}
