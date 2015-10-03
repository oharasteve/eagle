// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MoreParameters;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Property extends TokenSequence
{
	public @OPT TokenList<CSharp_Annotation> annotation;
	public @OPT TokenList<CSharp_PropertyModifier> modifier;
	public CSharp_Type type;
	public CSharp_Variable_Definition id;
	public @OPT CSharp_PropertySubscript subscript;
	
	public CSharp_Punctuation leftBrace = new CSharp_Punctuation('{');
	public @OPT TokenList<CSharp_GetterSetter> getSet;
	public CSharp_Punctuation rightBrace = new CSharp_Punctuation('}');
	
	public static class CSharp_PropertyModifier extends TokenSequence
	{
		public CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
	}
	
	public static class CSharp_PropertySubscript extends TokenSequence
	{
		public CSharp_Punctuation leftBracket = new CSharp_Punctuation('[');
		public @OPT CSharp_MethodParameter param;
		public @OPT TokenList<CSharp_MoreParameters> moreParams;
		public CSharp_Punctuation rightBracket = new CSharp_Punctuation(']');
	}
	
	public static class CSharp_GetterSetter extends TokenChooser
	{
		public static class CSharp_GetterNoBody extends TokenSequence
		{
			public CSharp_Keyword get = new CSharp_Keyword("get");
			public CSharp_Punctuation semi = new CSharp_Punctuation(';');
		}
		
		public static class CSharp_GetterBody extends TokenSequence
		{
			public CSharp_Keyword get = new CSharp_Keyword("get");
			public CSharp_Statement getBody;
		}

		public static class CSharp_SetterNoBody extends TokenSequence
		{
			public @OPT CSharp_Keyword csPrivate = new CSharp_Keyword("private");
			public CSharp_Keyword set = new CSharp_Keyword("set");
			public CSharp_Punctuation semi = new CSharp_Punctuation(';');
		}
		
		public static class CSharp_SetterBody extends TokenSequence
		{
			public CSharp_Keyword set = new CSharp_Keyword("set");
			public CSharp_Statement setBody;
		}
	}
}
