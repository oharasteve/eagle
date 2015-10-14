// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Data.CSharp_DataModifier;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Enum extends TokenSequence
{
	public @OPT TokenList<CSharp_Annotation> annotation;
	public @OPT @NEWLINE TokenList<CSharp_DataModifier> modifiers;
	public CSharp_Keyword ENUM = new CSharp_Keyword("enum");
	public CSharp_Variable_Definition id;
	public @OPT CSharp_Enum_Basetype baseType;
	public PunctuationLeftBrace leftBrace;
	public @OPT TokenList<CSharp_Comment> comments;
	public CSharp_Variable_Definition firstEnum;
	public @OPT CSharp_EnumInitializer initializer;
	public @OPT TokenList<CSharp_MoreEnums> moreEnums;
	public @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	public PunctuationRightBrace rightBrace;
	public @OPT PunctuationSemicolon semicolon;
	
	public static class CSharp_Enum_Basetype extends TokenSequence
	{
		public PunctuationColon colon;
		public CSharp_Type type;
	}
	
	public static class CSharp_MoreEnums extends TokenSequence
	{
		public PunctuationComma comma;
		public CSharp_Variable_Definition nextEnum;
		public @OPT CSharp_EnumInitializer initialize;
	}
	
	public static class CSharp_EnumInitializer extends TokenSequence
	{
		public PunctuationEquals equals;
		public CSharp_Expression expr;
	}
}
