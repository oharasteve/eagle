// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Function.C_FunctionParameter;
import com.eagle.programmar.C.C_Function.C_MoreParameterDefs;
import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypeUserDefined.C_TypeStar;
import com.eagle.programmar.C.Symbols.C_Field_Definition;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_Data extends TokenChooser
{
	public static class C_RegularData extends TokenSequence
	{
		public @OPT C_KeywordChoice scope = new C_KeywordChoice(C_Program.getModifiers());
		public C_Type jtype;
		public C_Variable_Definition id;
		public @OPT TokenList<C_Subscript> subscripts;
		public @OPT C_DataInitialValue initialValue;
		public @OPT TokenList<C_MoreIdentifiers> moreIds;
		public @NOSPACE PunctuationSemicolon semicolon;
		public @OPT TokenList<C_Comment> comments;
		
		public static class C_DataInitialValue extends TokenSequence
		{
			public PunctuationEquals equals;
			public C_Expression expression;
		}
		
		public static class C_MoreIdentifiers extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT TokenList<C_TypeStar> stars;
			public C_Variable_Definition id;
			public @OPT TokenList<C_Subscript> subscripts;
			public @OPT C_DataInitialValue initialValue;
		}
	}
	
	public static class C_FunctionPointer extends TokenSequence
	{
		public @OPT C_KeywordChoice scope = new C_KeywordChoice(C_Program.getModifiers());
		public C_Type jtype;
		public PunctuationLeftParen leftParen1;
		public PunctuationStar star;
		public C_Field_Definition id;
		public PunctuationRightParen rightParen1;
		public PunctuationLeftParen leftParen2;
		public @OPT @NOSPACE C_FunctionParameter param;
		public @OPT @NOSPACE TokenList<C_MoreParameterDefs> moreParams;
		public PunctuationRightParen rightParen2;
		public @NOSPACE @OPT PunctuationSemicolon semicolon;
	}
}
