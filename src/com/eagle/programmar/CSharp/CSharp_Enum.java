// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Data.CSharp_DataModifier;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Enum extends TokenSequence
{
	public @OPT TokenList<CSharp_Annotation> annotation;
	public @OPT @NEWLINE TokenList<CSharp_DataModifier> modifiers;
	public CSharp_Keyword ENUM = new CSharp_Keyword("enum");
	public CSharp_Variable_Definition id;
	public @OPT CSharp_Enum_Basetype baseType;
	public CSharp_Punctuation leftBrace = new CSharp_Punctuation('{');
	public @OPT TokenList<CSharp_Comment> comments;
	public CSharp_Variable_Definition firstEnum;
	public @OPT CSharp_EnumInitializer initializer;
	public @OPT TokenList<CSharp_MoreEnums> moreEnums;
	public @OPT @CURIOUS("Extra comma") CSharp_Punctuation comma = new CSharp_Punctuation(',');
	public CSharp_Punctuation rightBrace = new CSharp_Punctuation('}');
	public @OPT CSharp_Punctuation semicolon = new CSharp_Punctuation(';');
	
	public static class CSharp_Enum_Basetype extends TokenSequence
	{
		public CSharp_Punctuation colon = new CSharp_Punctuation(':');
		public CSharp_Type type;
	}
	
	public static class CSharp_MoreEnums extends TokenSequence
	{
		public CSharp_Punctuation comma = new CSharp_Punctuation(',');
		public CSharp_Variable_Definition nextEnum;
		public @OPT CSharp_EnumInitializer initialize;
	}
	
	public static class CSharp_EnumInitializer extends TokenSequence
	{
		public CSharp_Punctuation equals = new CSharp_Punctuation('=');
		public CSharp_Expression expr;
	}
}
