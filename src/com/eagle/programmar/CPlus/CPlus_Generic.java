// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CPlus_Generic extends TokenSequence
{
	public C_Punctuation lessThan = new C_Punctuation('<');
	public SeparatedList<C_Type,PunctuationComma> type;
	public C_Punctuation greaterThan = new C_Punctuation('>');
}
