// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CPlus_Class extends TokenSequence
{
	public C_Keyword CLASS = new C_Keyword("class");
	public CPlus_Class_Definition className;
	public @OPT CPlus_ClassExtendList extendsClasses;
	
	public static class CPlus_ClassExtendList extends TokenSequence
	{
		public PunctuationColon colon;
		public SeparatedList<CPlus_ClassExtends,PunctuationComma> extendsClasses;
		
		public static class CPlus_ClassExtends extends TokenSequence
		{
			public @OPT C_Keyword PUBLIC = new C_Keyword("public");
			public @OPT C_Punctuation colonColon = new C_Punctuation("::");
			public @OPT TokenList<CPlus_ExtendsNamespace> extendsNamespace;
			public C_Identifier_Reference otherClass;
			
			public static class CPlus_ExtendsNamespace extends TokenSequence
			{
				public C_Identifier_Reference otherNamespace;
				public C_Punctuation colonColon = new C_Punctuation("::");
			}
		}
	}
}
