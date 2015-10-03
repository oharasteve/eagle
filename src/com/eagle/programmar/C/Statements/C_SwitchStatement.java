// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_SwitchStatement extends TokenSequence
{
	public @DOC("#The-switch-Statement") C_Keyword SWITCH = new C_Keyword("switch");
	public C_Punctuation leftParen = new C_Punctuation('(');
	public C_Expression val;
	public C_Punctuation rightParen = new C_Punctuation(')');
	public @OPT C_Comment comment;
	public C_Punctuation leftBrace = new C_Punctuation('{');
	public TokenList<C_SwitchClause> switchClause;
	public C_Punctuation rightBrace = new C_Punctuation('}');
	
	public static class C_SwitchClause extends TokenChooser
	{
		public C_Comment comment;
		public C_CaseClause caseClause;
		public C_DefaultClause defaultClause;
	}
	
	public static class C_CaseClause extends TokenSequence
	{
		public C_Keyword CASE = new C_Keyword("case");
		public C_Expression expr;
		public C_Punctuation colon = new C_Punctuation(':');
		public @OPT TokenList<C_StatementOrComment> statements;
	}
	
	public static class C_DefaultClause extends TokenSequence
	{
		public C_Keyword DEFAULT = new C_Keyword("default");
		public C_Punctuation colon = new C_Punctuation(':');
		public @OPT TokenList<C_StatementOrComment> statements;
	}
}
