// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 15, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class VB_Variable extends TokenSequence
{
	public VB_Identifier_Reference var;
	public @OPT VB_Subscript subscript;
	public @OPT TokenList<VB_VariableField> dotFields;
	
	public static class VB_VariableField extends TokenSequence
	{
		public PunctuationPeriod dot;
		public VB_Identifier_Reference var;
		public @OPT VB_Subscript subscript;
	}
}
