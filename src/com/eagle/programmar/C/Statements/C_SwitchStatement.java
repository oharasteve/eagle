// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_SwitchStatement extends TokenSequence
{
	public @DOC("#The-switch-Statement") C_Keyword SWITCH = new C_Keyword("switch");
	public PunctuationLeftParen leftParen;
	public C_Expression val;
	public PunctuationRightParen rightParen;
	public @OPT C_Comment comment;
	public PunctuationLeftBrace leftBrace;
	public TokenList<C_SwitchClause> switchClause;
	public PunctuationRightBrace rightBrace;
	
	public static class C_SwitchClause extends TokenChooser
	{
		public @CHOICE C_Comment comment;
		public @CHOICE C_CaseClause caseClause;
		public @CHOICE C_DefaultClause defaultClause;
	}
	
	public static class C_CaseClause extends TokenSequence
	{
		public C_Keyword CASE = new C_Keyword("case");
		public C_Expression expr;
		public PunctuationColon colon;
		public @OPT TokenList<C_StatementOrComment> statements;
	}
	
	public static class C_DefaultClause extends TokenSequence
	{
		public C_Keyword DEFAULT = new C_Keyword("default");
		public PunctuationColon colon;
		public @OPT TokenList<C_StatementOrComment> statements;
	}
}
