// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Data extends TokenSequence
{
	public CSharp_DataBeforeSemicolon dataBody;
	public @NOSPACE PunctuationSemicolon semicolon;

	public static class CSharp_DataBeforeSemicolon extends TokenSequence
	{
		public @OPT TokenList<CSharp_Annotation> annotation1;
		public @OPT TokenList<CSharp_DataModifier> modifiers;
		public @OPT TokenList<CSharp_Annotation> annotation2;
		public CSharp_Type jtype;
		public CSharp_Variable_Definition id;
		public @OPT PunctuationLeftBracket leftBracket;
		public @OPT PunctuationRightBracket rightBracket;
		public @OPT CSharp_DataInitialValue initialValue;
		public @OPT TokenList<CSharp_MoreIdentifiers> moreIds;
	}
	
	public static class CSharp_DataModifier extends TokenSequence
	{
		public CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
	}
	
	public static class CSharp_DataInitialValue extends TokenSequence
	{
		public PunctuationEquals equals;
		public CSharp_Expression expression;
	}
	
	public static class CSharp_MoreIdentifiers extends TokenSequence
	{
		public PunctuationComma comma;
		public CSharp_Variable_Definition id;
		public @OPT PunctuationLeftBracket leftBracket;
		public @OPT PunctuationRightBracket rightBracket;
		public @OPT CSharp_DataInitialValue initialValue;
	}
}
