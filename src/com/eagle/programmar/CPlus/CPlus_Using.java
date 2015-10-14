// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 24, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CPlus_Using extends TokenSequence
{
	public C_Keyword USING = new C_Keyword("using");
	public @OPT C_Keyword NAMESPACE = new C_Keyword("namespace");
	public C_Expression what;
	public PunctuationSemicolon semicolon;
}
