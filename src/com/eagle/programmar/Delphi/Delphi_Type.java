// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_Number;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Type extends TokenChooser
{
	public @CHOICE Delphi_KeywordChoice base = new Delphi_KeywordChoice(
			"Boolean", "Integer", "LongInt", "Int64", "Text");
	
	public @CHOICE Delphi_Identifier_Reference userType;
	public @CHOICE Delphi_Class classDefinition;
	
	public @CHOICE static class Delphi_Enum extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public SeparatedList<Delphi_EnumValue,PunctuationComma> enumValues;
		public PunctuationRightParen rightParen;
		
		public static class Delphi_EnumValue extends TokenSequence
		{
			public Delphi_Variable_Definition name;
			public PunctuationEquals equals;
			public Delphi_Expression value;
		}
	}
	
	public @CHOICE static class Delphi_Array extends TokenSequence
	{
		public Delphi_Keyword ARRAY = new Delphi_Keyword("Array");
		public @OPT Delphi_ArraySize size;
		public Delphi_Keyword OF = new Delphi_Keyword("Of");
		public Delphi_Type type;
		
		public static class Delphi_ArraySize extends TokenSequence
		{
			public PunctuationLeftBracket leftBracket;
			public SeparatedList<Delphi_Expression,PunctuationComma> subscripts;
			public PunctuationRightBracket rightBracket;
		}
	}
	
	public @CHOICE static class Delphi_Range extends TokenSequence
	{
		public Delphi_Number low;
		public Delphi_Punctuation dotDot = new Delphi_Punctuation("..");
		public Delphi_Number high;
	}
	
	public @CHOICE static class Delphi_Type_Record extends TokenSequence
	{
		public Delphi_Keyword RECORD = new Delphi_Keyword("Record");
		public TokenList<Delphi_RecordEntry> entries;
		public Delphi_Keyword END = new Delphi_Keyword("End");

		public static class Delphi_RecordEntry extends TokenSequence
		{
			public SeparatedList<Delphi_Variable_Definition,PunctuationComma> vars;
			public PunctuationColon colon;
			public Delphi_Type type;
			public PunctuationSemicolon semicolon;
		}
	}
	
	public @FIRST static class Delphi_String extends TokenSequence
	{
		public Delphi_Keyword STRING = new Delphi_Keyword("String");
		public PunctuationLeftBracket leftBracket;
		public Delphi_Expression expr;
		public PunctuationRightBracket rightBracket;		
	}
}
