// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_PercentStatement extends TokenSequence
{
	public PLI_Punctuation percent = new PLI_Punctuation('%');
	public PLI_PercentWhat what;
	public PunctuationSemicolon semicolon1;
	public @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon2;
	
	public static class PLI_PercentWhat extends TokenChooser
	{
		public static class PLI_PercentAssignment extends TokenSequence
		{
			public PLI_Variable_Definition var;
			public PunctuationEquals equals;
			public PLI_Expression expr;
		}
		
		public static class PLI_PercentProcess extends TokenSequence
		{
			public @DOC("7.43") PLI_Keyword PROCESS = new PLI_Keyword("PROCESS");
			public PLI_Keyword GOSTMT = new PLI_Keyword("GOSTMT");
		}
		
		public static class PLI_PercentInclude extends TokenSequence
		{
			public @DOC("7.29") PLI_Keyword INCLUDE = new PLI_Keyword("INCLUDE");
			public PLI_ProcessIncludeWhat what;
			
			public static class PLI_ProcessIncludeWhat extends TokenChooser
			{
				public PLI_Literal literal;
				public PLI_Identifier_Reference var;
			}
		}
		
		public static class PLI_PercentDeclare extends TokenSequence
		{
			public @DOC("7.10") PLI_Keyword DECLARE = new PLI_Keyword("DECLARE");
			public @OPT PunctuationLeftParen leftParen;
			public SeparatedList<PLI_Variable_Definition,PunctuationComma> vars;
			public @OPT PunctuationRightParen rightParen;
			public PLI_KeywordChoice type = new PLI_KeywordChoice("FIXED", "CHARACTER");
		}
		
		public static class PLI_PercentActivate extends TokenSequence
		{
			public PLI_Keyword ACTIVATE = new PLI_Keyword("ACTIVATE");
			public SeparatedList<PLI_Identifier_Reference,PunctuationComma> vars;
			public @OPT PLI_Keyword NORESCAN = new PLI_Keyword("NORESCAN");
		}
		
		public static class PLI_PercentDeactivate extends TokenSequence
		{
			public @DOC("7.8") PLI_Keyword DEACTIVATE = new PLI_Keyword("DEACTIVATE");
			public PLI_Identifier_Reference var;
		}
		
		public static class PLI_PercentSkip extends TokenSequence
		{
			public PLI_Keyword SKIP = new PLI_Keyword("SKIP");
			public PunctuationLeftParen leftParen;
			public PLI_Number number;
			public PunctuationRightParen rightParen;
		}
	}
}
