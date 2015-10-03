// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Statements.PLI_PercentStatement.PLI_PercentWhat.PLI_PercentActivate.PLI_DeclareMore;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_PercentStatement extends TokenSequence
{
	public PLI_Punctuation percent = new PLI_Punctuation('%');
	public PLI_PercentWhat what;
	public PLI_Punctuation semicolon1 = new PLI_Punctuation(';');
	public @OPT @CURIOUS("Extra semicolon") PLI_Punctuation semiColon2 = new PLI_Punctuation(';');
	
	public static class PLI_PercentWhat extends TokenChooser
	{
		public static class PLI_PercentAssignment extends TokenSequence
		{
			public PLI_Variable_Definition var;
			public PLI_Punctuation equals = new PLI_Punctuation('=');
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
			public @OPT PLI_Punctuation leftParen = new PLI_Punctuation('(');
			public PLI_Variable_Definition var;
			public @OPT TokenList<PLI_DeclareMore> more;
			public @OPT PLI_Punctuation rightParen = new PLI_Punctuation(')');
			public PLI_KeywordChoice type = new PLI_KeywordChoice("FIXED", "CHARACTER");
		}
		
		public static class PLI_PercentActivate extends TokenSequence
		{
			public PLI_Keyword ACTIVATE = new PLI_Keyword("ACTIVATE");
			public PLI_Identifier_Reference var;
			public @OPT TokenList<PLI_DeclareMore> more;
			public @OPT PLI_Keyword NORESCAN = new PLI_Keyword("NORESCAN");
			
			public static class PLI_DeclareMore extends TokenSequence
			{
				public PLI_Punctuation comma = new PLI_Punctuation(',');
				public PLI_Identifier_Reference var;
			}
		}
		
		public static class PLI_PercentDeactivate extends TokenSequence
		{
			public @DOC("7.8") PLI_Keyword DEACTIVATE = new PLI_Keyword("DEACTIVATE");
			public PLI_Identifier_Reference var;
		}
		
		public static class PLI_PercentSkip extends TokenSequence
		{
			public PLI_Keyword SKIP = new PLI_Keyword("SKIP");
			public PLI_Punctuation leftParen = new PLI_Punctuation('(');
			public PLI_Number number;
			public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		}
	}
}
