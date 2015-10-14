// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class VB_AttributeStatement extends TokenSequence
{
	public VB_Keyword ATTRIBUTE = new VB_Keyword("attribute");
	public VB_Identifier_Reference name;
	public PunctuationEquals equals;
	public VB_Expression value;
}
