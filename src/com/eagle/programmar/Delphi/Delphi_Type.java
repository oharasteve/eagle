// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_Number;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Type extends TokenChooser
{
	public Delphi_KeywordChoice base = new Delphi_KeywordChoice(
			"Boolean", "Integer", "LongInt", "Int64", "Text");
	
	public Delphi_Identifier_Reference userType;
	public Delphi_Class classDefinition;
	
	public static class Delphi_Enum extends TokenSequence
	{
		public Delphi_Punctuation leftParen = new Delphi_Punctuation('(');
		public Delphi_EnumValue enumValue;
		public @OPT TokenList<Delphi_MoreEnums> moreEnums;
		public Delphi_Punctuation rightParen = new Delphi_Punctuation(')');
		
		public static class Delphi_EnumValue extends TokenSequence
		{
			public Delphi_Variable_Definition name;
			public Delphi_Punctuation equals = new Delphi_Punctuation('=');
			public Delphi_Expression value;
		}
		
		public static class Delphi_MoreEnums extends TokenSequence
		{
			public Delphi_Punctuation comma = new Delphi_Punctuation(',');
			public Delphi_EnumValue enumValue;
		}
	}
	
	public static class Delphi_Array extends TokenSequence
	{
		public Delphi_Keyword ARRAY = new Delphi_Keyword("Array");
		public @OPT Delphi_ArraySize size;
		public Delphi_Keyword OF = new Delphi_Keyword("Of");
		public Delphi_Type type;
		
		public static class Delphi_ArraySize extends TokenSequence
		{
			public Delphi_Punctuation leftBracket = new Delphi_Punctuation('[');
			public Delphi_Expression lowSubscript;
			public Delphi_Punctuation dotDot = new Delphi_Punctuation("..");
			public Delphi_Expression highSubscript;
			public Delphi_Punctuation rightBracket = new Delphi_Punctuation(']');
		}
	}
	
	public static class Delphi_Type_Range extends TokenSequence
	{
		public Delphi_Number low;
		public Delphi_Punctuation dotDot = new Delphi_Punctuation("..");
		public Delphi_Number high;
	}
	
	public static class Delphi_Type_Record extends TokenSequence
	{
		public Delphi_Keyword RECORD = new Delphi_Keyword("Record");
		public TokenList<Delphi_RecordEntry> entries;
		public Delphi_Keyword END = new Delphi_Keyword("End");

		public static class Delphi_RecordEntry extends TokenSequence
		{
			public Delphi_Variable_Definition var;
			public @OPT TokenList<Delphi_MoreVars> more;
			public Delphi_Punctuation colon = new Delphi_Punctuation(':');
			public Delphi_Type type;
			public Delphi_Punctuation semicolon = new Delphi_Punctuation(';');
			
			public static class Delphi_MoreVars extends TokenSequence
			{
				public Delphi_Punctuation comma = new Delphi_Punctuation(',');
				public Delphi_Variable_Definition var;
			}
		}
	}
}
