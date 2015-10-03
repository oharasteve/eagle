// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Data.C_FunctionPointer;
import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypeStruct.C_FieldOrComment;
import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypeUserDefined.C_TypeStar;
import com.eagle.programmar.C.Symbols.C_Field_Definition;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Symbols.C_Type_Definition;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_Type extends TokenSequence
{
	public C_TypeBase base;
	public @OPT C_TypeGeneric generic;
	public @OPT C_TypeFunction function;
	
	public static class C_TypeBase extends TokenChooser
	{
		public @FIRST static class C_NamespaceType extends TokenSequence
		{
			public C_Identifier_Reference namespace;
			public C_Punctuation colonColon = new C_Punctuation("::");
			public C_TypeBase typebase;
		}

		public static class C_TypeUnion extends TokenSequence
		{
			public C_Keyword UNION = new C_Keyword("union");
			public C_Punctuation leftBrace = new C_Punctuation('{');
			public @OPT TokenList<C_FieldOrComment> fields;
			public C_Punctuation rightBrace = new C_Punctuation('}');
			public C_Punctuation semicolon = new C_Punctuation(';');
		}
		
		public static class C_TypeStruct extends TokenSequence
		{
			public @OPT TokenList<C_Comment> comments;
			public C_Keyword STRUCT = new C_Keyword("struct");
			public @OPT C_Comment comment1;
			public @OPT C_Type_Definition def;
			public @OPT C_Comment comment2;
			public C_Punctuation leftBrace = new C_Punctuation('{');
			public @OPT TokenList<C_FieldOrComment> fields;
			public C_Punctuation rightBrace = new C_Punctuation('}');
			public @OPT @CURIOUS("Extra semicolon") C_Punctuation semicolon = new C_Punctuation(';');
			
			public static class C_FieldOrComment extends TokenChooser
			{
				public C_Comment comment;
				public C_FunctionPointer functionPtr;
				public @LAST C_TypeUnion union;
				
				public static class C_Field extends TokenSequence
				{
					public C_Type jtype;
					public C_Field_Definition id;
					public @OPT TokenList<C_Subscript> subscripts;
					public @OPT C_FieldInitialValue initialValue;
					public @OPT TokenList<C_MoreFields> more;
					public @NOSPACE C_Punctuation semicolon = new C_Punctuation(';');
					public @OPT TokenList<C_Comment> comments;
					
					public static class C_MoreFields extends TokenSequence
					{
						public C_Punctuation comma = new C_Punctuation(',');
						public @OPT C_Punctuation star = new C_Punctuation('*');
						public C_Field_Definition id;
						public @OPT TokenList<C_Subscript> subscripts;
						public @OPT C_FieldInitialValue initialValue;
					}
					
					public static class C_FieldInitialValue extends TokenSequence
					{
						public C_Punctuation equals = new C_Punctuation('=');
						public C_Expression expression;
					}
				}
			}
		}

		public static class C_TypePrimitive extends TokenSequence
		{
			public @OPT C_Keyword CONST = new C_Keyword("const");
			public @OPT C_Keyword UNSIGNED = new C_Keyword("unsigned");
			public C_KeywordChoice primitive = new C_KeywordChoice(C_Program.getPrimitives());
			public @OPT C_Keyword INT = new C_Keyword("int");
			public @OPT TokenList<C_TypeStar> stars;
		}
		
		// This one isn't handled by C_TypePrimitive
		public @FIRST static class C_TypeShortUnsignedInt extends TokenSequence
		{
			public C_KeywordChoice SHORT = new C_KeywordChoice("long", "short");
			public C_Keyword UNSIGNED = new C_Keyword("unsigned");
			public C_Keyword INT = new C_Keyword("int");
		}
		
		public static class C_TypeUserDefined extends TokenSequence
		{
			public @OPT C_Keyword STRUCT = new C_Keyword("struct");
			public C_Identifier_Reference typeName;
			public @OPT TokenList<C_TypeStar> stars;
			
			public static class C_TypeStar extends TokenSequence
			{
				public C_PunctuationChoice starAmpersand = new C_PunctuationChoice("*", "&");
			}
		}
		
		public static class C_TypeEnum extends TokenSequence
		{
			public C_Keyword ENUM = new C_Keyword("enum");
			public @OPT C_Identifier_Reference typeName;
			public C_Punctuation leftBrace = new C_Punctuation('{');
			public C_Variable_Definition firstEnum;
			public @OPT C_EnumInitializer init;
			public @OPT C_Comment comment;
			public @OPT TokenList<C_MoreEnums> moreEnums;
			public C_Punctuation rightBrace = new C_Punctuation('}');
			
			public static class C_MoreEnums extends TokenSequence
			{
				public C_Punctuation comma = new C_Punctuation(',');
				public C_Variable_Definition nextEnum;
				public @OPT C_EnumInitializer init;
				public @OPT C_Comment comment;
			}
			
			public static class C_EnumInitializer extends TokenSequence
			{
				public C_Punctuation equals = new C_Punctuation('=');
				public C_Expression initialValue;
			}
		}
	}
	
	public static class C_TypeGeneric extends TokenSequence
	{
		public C_Punctuation lessThan = new C_Punctuation('<');
		public C_Type type;
		public @OPT TokenList<C_MoreTypeGeneric> more;
		public C_Punctuation greaterThan = new C_Punctuation('>');
		
		public static class C_MoreTypeGeneric extends TokenSequence
		{
			public C_Punctuation comma = new C_Punctuation(',');
			public C_Type type;
		}
	}
	
	public static class C_TypeFunction extends TokenSequence
	{
		public C_Punctuation leftParen = new C_Punctuation('(');
		public C_Punctuation star = new C_Punctuation('*');
		public C_Punctuation rightParen = new C_Punctuation(')');
		public C_Function_ParameterDefs params;
	}
}