	// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.Symbols.C_Type_Definition;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_TypeDef extends TokenSequence
{
	public C_Keyword TYPEDEF = new C_Keyword("typedef");
	public @OPT C_Keyword INTERFACE = new C_Keyword("interface");
	public C_TypeDef_What what;
	public C_Punctuation semicolon = new C_Punctuation(';');
	
	public static class C_TypeDef_What extends TokenChooser
	{
		public static class C_TypeDef_Data extends TokenSequence
		{
			public C_Type type;
			public @OPT C_Punctuation star = new C_Punctuation('*');
			public C_Type_Definition typeName;
			public @OPT TokenList<C_TypeDefMore> more;
			
			public static class C_TypeDefMore extends TokenSequence
			{
				public C_Punctuation comma = new C_Punctuation(',');
				public @OPT C_Punctuation star = new C_Punctuation('*');
				public C_Type_Definition typeName;
			}
		}
		
		public static class C_TypeDef_Function extends TokenSequence
		{
			public C_Type type;
			public @OPT C_Punctuation star1 = new C_Punctuation('*');
			public C_Punctuation leftParen1 = new C_Punctuation('(');
			public C_Punctuation star2 = new C_Punctuation('*');
			public C_Type_Definition typeName;
			public C_Punctuation rightParen1 = new C_Punctuation(')');
			public C_Function_ParameterDefs params;
		}
	}
}
