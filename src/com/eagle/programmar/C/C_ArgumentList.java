// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypePrimitive;
import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypeUserDefined.C_TypeStar;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class C_ArgumentList  extends TokenSequence
{
	public C_ExpressionArg arg;
	public @OPT C_Comment comment;
	public @OPT TokenList<C_MoreArguments> moreArgs;
	public @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	
	public static class C_ExpressionArg extends TokenChooser
	{
		public @FIRST C_Expression expr;
		public @CHOICE C_Keyword CONST = new C_Keyword("const");
		public @CHOICE C_TypePrimitive primitiveType;
		
		public @CHOICE static class C_ExpressionArgType extends TokenSequence
		{
			public C_Identifier_Reference typeRef;
			public TokenList<C_TypeStar> stars;
		}
	}
	
	public static class C_MoreArguments extends TokenSequence
	{
		public PunctuationComma comma;
		public @OPT C_Comment comment1;
		public C_ExpressionArg arg;
		public @OPT C_Comment comment2;
	}
}
