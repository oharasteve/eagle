// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Symbols.Delphi_Program_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Include;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Program extends EagleLanguage
{
	public static final String NAME = "Delphi";
	
	public Delphi_Program()
	{
		super(NAME, new Delphi_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://docwiki.embarcadero.com/RADStudio/en/Declarations_and_Statements/";
	}
	
	public Delphi_Full_or_Partial fullOrPartial;
	
	public static class Delphi_Full_or_Partial extends TokenChooser
	{
		public @CHOICE static class Delphi_Full extends TokenSequence
		{
			public Delphi_KeywordChoice programOrUnit = new Delphi_KeywordChoice("Program", "Unit");
			public Delphi_Program_Definition id;
			public PunctuationSemicolon semicolon;
			public @OPT TokenList<Delphi_Header> headers;
			public @OPT Delphi_BeginEnd beginEnd;
			public @OPT Delphi_Keyword END = new Delphi_Keyword("End");
			public PunctuationPeriod dot;
		}
		
		public @CHOICE static class Delphi_Partial extends TokenSequence
		{
			public TokenList<Delphi_Header> headers;
		}
	}
	
	public static class Delphi_Header extends TokenChooser
	{
		public @CHOICE Delphi_KeywordChoice INTERFACE = new Delphi_KeywordChoice("Interface", "Implementation");
		
		public @CHOICE Delphi_Comment comment;
		
		public @CHOICE Delphi_Uses uses;
		public @CHOICE Delphi_Types types;
		public @CHOICE Delphi_Consts consts;
		public @CHOICE Delphi_Vars vars;
		public @CHOICE Delphi_Procedure proc;
		public @CHOICE Delphi_Function func;
		public @CHOICE Delphi_Include include;
		
		public @CHOICE static class Delphi_Initialization extends TokenSequence
		{
			public Delphi_KeywordChoice INITIALIZATION = new Delphi_KeywordChoice("Initialization", "Finalization");
			public Delphi_Statement stmt;
			public PunctuationSemicolon semicolon;
		}
	}
}
