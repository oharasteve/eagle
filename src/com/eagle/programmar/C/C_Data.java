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
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

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
		public @NOSPACE C_Punctuation semicolon = new C_Punctuation(';');
		public @OPT TokenList<C_Comment> comments;
		
		public static class C_DataInitialValue extends TokenSequence
		{
			public C_Punctuation equals = new C_Punctuation('=');
			public C_Expression expression;
		}
		
		public static class C_MoreIdentifiers extends TokenSequence
		{
			public C_Punctuation comma = new C_Punctuation(',');
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
		public C_Punctuation leftParen1 = new C_Punctuation('(');
		public C_Punctuation star = new C_Punctuation('*');
		public C_Field_Definition id;
		public C_Punctuation rightParen1 = new C_Punctuation(')');
		public C_Punctuation leftParen2 = new C_Punctuation('(');
		public @OPT @NOSPACE C_FunctionParameter param;
		public @OPT @NOSPACE TokenList<C_MoreParameterDefs> moreParams;
		public C_Punctuation rightParen2 = new C_Punctuation(')');
		public @NOSPACE @OPT C_Punctuation semicolon = new C_Punctuation(';');
	}
}
