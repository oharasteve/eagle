// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Subscript;
import com.eagle.programmar.VB.VB_Type;
import com.eagle.programmar.VB.Symbols.VB_Variable_Definition;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class VB_DataDeclaration extends TokenSequence
{
	public VB_KeywordChoice modifier = new VB_KeywordChoice(
			"private", "public", "dim", "const");
	public @OPT VB_Keyword CONST = new VB_Keyword("const");
	public VB_Variable_Definition var;
	public @OPT VB_Subscript subscript;
	public @OPT TokenList<VB_MoreVariables> moreVariables;
	public @OPT VB_DataType dataType;
	public @OPT VB_DataInitialization initializer;

	public static class VB_DataType extends TokenSequence
	{
		public VB_Keyword AS = new VB_Keyword("as");
		public VB_Type type;
	}

	public static class VB_DataInitialization extends TokenSequence
	{
		public PunctuationEquals equals;
		public VB_Expression expr;
	}
	
	public static class VB_MoreVariables extends TokenSequence
	{
		public PunctuationComma comma;
		public VB_Variable_Definition var;
		public @OPT VB_Subscript subscript;
	}
}
