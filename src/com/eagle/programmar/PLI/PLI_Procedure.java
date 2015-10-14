// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Procedure_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class PLI_Procedure extends TokenSequence
{
	public @OPT PLI_Signals signals;
	public @OPT PLI_Punctuation percent1 = new PLI_Punctuation('%');
	public PLI_Procedure_Definition id1;
	public PunctuationColon colon;
	
	public PLI_KeywordChoice PROCEDURE = new PLI_KeywordChoice("PROCEDURE", "PROC");
	public @OPT PLI_Procedure_Parameters params;
	
	public @OPT PLI_ProcedureOptions options1;
	public @OPT PLI_Keyword RECURSIVE = new PLI_Keyword("RECURSIVE");
	public @OPT PLI_ProcedureReturns returns;
	public @OPT PLI_ProcedureOptions options2;
	public PunctuationSemicolon semicolon1;

	public TokenList<PLI_StatementOrComment> statements;
	
	public @OPT PLI_Punctuation percent2 = new PLI_Punctuation('%');
	public PLI_Keyword END = new PLI_Keyword("END");
	public PLI_Identifier_Reference id2;
	public PunctuationSemicolon semicolon2;
	
	public static class PLI_Procedure_Parameters extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT PunctuationStar star;
		public @OPT SeparatedList<PLI_Identifier_Reference,PunctuationComma> params;
		public PunctuationRightParen rightParen;
	}
	
	public static class PLI_ProcedureOptions extends TokenSequence
	{
		public PLI_Keyword OPTIONS = new PLI_Keyword("OPTIONS");
		public PunctuationLeftParen leftParen;
		public @OPT PLI_Keyword MAIN = new PLI_Keyword("MAIN");
		public @OPT PunctuationComma comma;
		public @OPT PLI_KeywordChoice order = new PLI_KeywordChoice(
				"ORDER", "REORDER");
		public PunctuationRightParen rightParen;
	}
	
	public static class PLI_ProcedureReturns extends TokenSequence
	{
		public PLI_Keyword RETURNS = new PLI_Keyword("RETURNS");
		public PunctuationLeftParen leftParen;
		public PLI_Type type;
		public @OPT PLI_Keyword BYADDR = new PLI_Keyword("BYADDR");
		public PunctuationRightParen rightParen;
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
