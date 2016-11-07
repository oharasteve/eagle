// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Function.C_FunctionBody;
import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CPlus.Symbols.CPlus_Current_Class_Reference;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CPlus_Method extends TokenSequence
{
	public CPlus_MethodTypeAndName typeAndName;
	public C_Function_ParameterDefs parameters;
	public @OPT TokenList<C_Comment> comments2;
	public C_FunctionBody body;

	public static class CPlus_MethodTypeAndName extends TokenChooser
	{
		public @CHOICE static class CPlus_MethodWithType extends TokenSequence
		{
			public C_Type type;
			public TokenList<CPlus_NamespaceQualifier> nameSpaces;
			public C_Identifier_Reference methodName;
		}
		
		public @CHOICE static class CPlus_MethodConstructor extends TokenSequence
		{
			public TokenList<CPlus_NamespaceQualifier> nameSpaces;
			public @OPT C_Punctuation tilde = new C_Punctuation('~');
			public CPlus_Current_Class_Reference methodName;
		}

		public @LAST static class CPlus_MethodNoType extends TokenSequence
		{
			public TokenList<CPlus_NamespaceQualifier> nameSpaces;
			public @OPT C_Punctuation tilde = new C_Punctuation('~');
			public C_Identifier_Reference methodName;
		}
	}
	
	public static class CPlus_NamespaceQualifier extends TokenSequence
	{
		public C_Identifier_Reference nameSpace;
		public @OPT CPlus_Generic generic;
		public C_Punctuation colonColon = new C_Punctuation("::");
	}
}
