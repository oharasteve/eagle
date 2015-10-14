// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Rewrite_Statement extends TokenSequence
{
	public Delphi_Keyword REWRITE = new Delphi_Keyword("ReWrite");
	public PunctuationLeftParen leftParen;
	public Delphi_Identifier_Reference file;
	public PunctuationComma comma;
	public Delphi_Literal fileName;
	public PunctuationRightParen rightParen;
}
