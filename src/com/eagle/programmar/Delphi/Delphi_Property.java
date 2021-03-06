// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Symbols.Delphi_Property_Definition;
import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Property extends TokenSequence
{
	public Delphi_Keyword PROPERTY = new Delphi_Keyword("Property");
	public Delphi_Property_Definition property;
	public @OPT Delphi_PropertySubscript subscript;
	public PunctuationColon colon;
	public Delphi_Type type;
	public TokenList<Delphi_PropertyReadWrite> readWrites;
	public PunctuationSemicolon semicolon;
	
	public static class Delphi_PropertyReadWrite extends TokenChooser
	{
		public @CHOICE static class Delphi_PropertyRead extends TokenSequence
		{
			public Delphi_Keyword READ = new Delphi_Keyword("Read");
			public Delphi_Identifier_Reference readVar;
		}
		
		public @CHOICE static class Delphi_PropertyWrite extends TokenSequence
		{
			public Delphi_Keyword WRITE = new Delphi_Keyword("Write");
			public Delphi_Identifier_Reference writeVar;
		}
	}
	
	public static class Delphi_PropertySubscript extends TokenSequence
	{
		public PunctuationLeftBracket leftBracket;
		public Delphi_Variable_Definition var;
		public PunctuationColon colon;
		public Delphi_Type type;
		public PunctuationRightBracket rightBracket;
	}

}
