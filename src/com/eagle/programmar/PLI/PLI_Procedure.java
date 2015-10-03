// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Procedure_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_Procedure extends TokenSequence
{
	public @OPT PLI_Signals signals;
	public @OPT PLI_Punctuation percent1 = new PLI_Punctuation('%');
	public PLI_Procedure_Definition id1;
	public PLI_Punctuation colon = new PLI_Punctuation(':');
	
	public PLI_KeywordChoice PROCEDURE = new PLI_KeywordChoice("PROCEDURE", "PROC");
	public @OPT PLI_Procedure_Parameters params;
	
	public @OPT PLI_ProcedureOptions options1;
	public @OPT PLI_Keyword RECURSIVE = new PLI_Keyword("RECURSIVE");
	public @OPT PLI_ProcedureReturns returns;
	public @OPT PLI_ProcedureOptions options2;
	public PLI_Punctuation semicolon = new PLI_Punctuation(';');

	public TokenList<PLI_StatementOrComment> statements;
	
	public @OPT PLI_Punctuation percent2 = new PLI_Punctuation('%');
	public PLI_Keyword END = new PLI_Keyword("END");
	public PLI_Identifier_Reference id2;
	public PLI_Punctuation semiColon = new PLI_Punctuation(';');
	
	public static class PLI_Procedure_Parameters extends TokenSequence
	{
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public @OPT PLI_Punctuation star = new PLI_Punctuation('*');
		public @OPT PLI_Identifier_Reference param;
		public @OPT TokenList<PLI_MoreParameters> moreParams;
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');

		public static class PLI_MoreParameters extends TokenSequence
		{
			public PLI_Punctuation comma = new PLI_Punctuation(',');
			public PLI_Identifier_Reference param;
		}
	}
	
	public static class PLI_ProcedureOptions extends TokenSequence
	{
		public PLI_Keyword OPTIONS = new PLI_Keyword("OPTIONS");
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public @OPT PLI_Keyword MAIN = new PLI_Keyword("MAIN");
		public @OPT PLI_Punctuation comma = new PLI_Punctuation(',');
		public @OPT PLI_KeywordChoice order = new PLI_KeywordChoice(
				"ORDER", "REORDER");
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
	}
	
	public static class PLI_ProcedureReturns extends TokenSequence
	{
		public PLI_Keyword RETURNS = new PLI_Keyword("RETURNS");
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public PLI_Type type;
		public @OPT PLI_Keyword BYADDR = new PLI_Keyword("BYADDR");
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
	}
	
	public static class PLI_StatementOrComment extends TokenChooser
	{
		public @FIRST PLI_Entry entry;
		public PLI_Comment comment;
		public PLI_Statement statement;
		public PLI_Declaration declaration;
		public PLI_Signals signals;
	}
}
