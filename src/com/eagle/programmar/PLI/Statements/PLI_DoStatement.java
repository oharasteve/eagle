// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_DoStatement extends TokenSequence
{
	public @OPT PLI_Label label1;
	public @DOC("7.15") PLI_Keyword DO = new PLI_Keyword("DO");
	public @OPT PLI_DoLoop loop;
	public @OPT PLI_DoUntil until;
	public @OPT PLI_DoWhile dowhile;
	public @OPT PLI_Keyword FOREVER = new PLI_Keyword("FOREVER");
	public PunctuationSemicolon semicolon1;
	public @OPT TokenList<PLI_StatementOrComment> statements;
	public PLI_Keyword END = new PLI_Keyword("END");
	public @OPT PLI_Identifier_Reference label2;
	public PunctuationSemicolon semicolon2;
	
	public static class PLI_DoLoop extends TokenSequence
	{
		public PLI_Identifier_Reference var;
		public PunctuationEquals equals;
		public PLI_Expression fromExpr;
		public PLI_Keyword TO = new PLI_Keyword("TO");
		public PLI_Expression toExpr;
		public @OPT PLI_DoBy by;
		
		public static class PLI_DoBy extends TokenSequence
		{
			public PLI_Keyword BY = new PLI_Keyword("BY");
			public PLI_Expression byExpr;
		}
	}
	
	public static class PLI_DoUntil extends TokenSequence
	{
		public PLI_Keyword UNTIL = new PLI_Keyword("UNTIL");
		public PLI_Expression condition;
	}
	
	public static class PLI_DoWhile extends TokenSequence
	{
		public PLI_Keyword WHILE = new PLI_Keyword("WHILE");
		public PLI_Expression condition;
	}
}
