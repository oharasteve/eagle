// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF;

import com.eagle.programmar.BNF.Symbols.BNF_Rule_Definition;
import com.eagle.programmar.BNF.Terminals.BNF_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class BNF_Rule extends TokenSequence
{
	public BNF_Rule_Definition definition;
	public BNF_Punctuation coloncolonEquals = new BNF_Punctuation("::=");
	public TokenList<BNF_Expression> expressions;
	public BNF_Punctuation semicolon = new BNF_Punctuation(";");
}
