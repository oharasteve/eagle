	// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.Symbols.C_Function_Definition;
import com.eagle.programmar.C.Symbols.C_Type_Definition;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_TypeDef extends TokenSequence
{
	public C_Keyword TYPEDEF = new C_Keyword("typedef");
	public @OPT C_Keyword INTERFACE = new C_Keyword("interface");
	public C_TypeDef_What what;
	public PunctuationSemicolon semicolon;
	
	public static class C_TypeDef_What extends TokenChooser
	{
		public @SYNTAX(CMacro_Syntax.class) CMacro_Processable macro;
		
		public static class C_TypeDef_Data extends TokenSequence
		{
			public C_Type type;
			public @OPT PunctuationStar star;
			public C_Type_Definition typeName;
			public @OPT TokenList<C_TypeDefMore> more;
			
			public static class C_TypeDefMore extends TokenSequence
			{
				public PunctuationComma comma;
				public @OPT PunctuationStar star;
				public C_Type_Definition typeName;
			}
		}
		
		public @LAST static class C_TypeDef_Function extends TokenSequence
		{
			public C_Type returnType;
			public PunctuationLeftParen leftParen;
			public PunctuationStar star;
			public C_Function_Definition funcName;
			public PunctuationRightParen rightParen;
			public C_Function_ParameterDefs params;
		}

		// Just like C_TypeDef_Function but no parens ... I didn't know this was valid syntax!
		public @FIRST static class C_TypeDef_NoParensFunction extends TokenSequence
		{
			public C_Type returnType;
			public C_Function_Definition funcName;
			public C_Function_ParameterDefs params;
		}
	}
}
