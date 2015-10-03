// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Symbols.Delphi_Property_Definition;
import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Property extends TokenSequence
{
	public Delphi_Keyword PROPERTY = new Delphi_Keyword("Property");
	public Delphi_Property_Definition property;
	public @OPT Delphi_PropertySubscript subscript;
	public Delphi_Punctuation colon = new Delphi_Punctuation(':');
	public Delphi_Type type;
	public TokenList<Delphi_PropertyReadWrite> readWrites;
	public Delphi_Punctuation semicolon = new Delphi_Punctuation(';');
	
	public static class Delphi_PropertyReadWrite extends TokenChooser
	{
		public static class Delphi_PropertyRead extends TokenSequence
		{
			public Delphi_Keyword READ = new Delphi_Keyword("Read");
			public Delphi_Identifier_Reference readVar;
		}
		
		public static class Delphi_PropertyWrite extends TokenSequence
		{
			public Delphi_Keyword WRITE = new Delphi_Keyword("Write");
			public Delphi_Identifier_Reference writeVar;
		}
	}
	
	public static class Delphi_PropertySubscript extends TokenSequence
	{
		public Delphi_Punctuation leftBracket = new Delphi_Punctuation('[');
		public Delphi_Variable_Definition var;
		public Delphi_Punctuation colon = new Delphi_Punctuation(':');
		public Delphi_Type type;
		public Delphi_Punctuation rightBracket = new Delphi_Punctuation(']');
	}

}
