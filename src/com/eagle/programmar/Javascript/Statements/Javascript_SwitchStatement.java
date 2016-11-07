// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Statement.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_SwitchStatement extends TokenSequence
{
	public @DOC("js_switch.asp") Javascript_Keyword SWITCH = new Javascript_Keyword("switch");
	public PunctuationLeftParen leftParen;
	public @NOSPACE Javascript_Expression val;
	public @NOSPACE PunctuationRightParen rightParen;
	public @INDENT PunctuationLeftBrace leftBrace;
	public TokenList<Javascript_CaseClause> caseClause;
	public @OPT Javascript_DefaultClause elseClause;
	public @OUTDENT PunctuationRightBrace rightBrace;
	
	public static class Javascript_CaseClause extends TokenSequence
	{
		public @BLANKLINE Javascript_Keyword CASE = new Javascript_Keyword("case");
		public Javascript_Expression expr;
		public PunctuationColon colon;
		public @OPT TokenList<Javascript_StatementOrComment> statements;
	}
	
	public static class Javascript_DefaultClause extends TokenSequence
	{
		public @NEWLINE Javascript_Keyword DEFAULT = new Javascript_Keyword("default");
		public PunctuationColon colon;
		public TokenList<Javascript_StatementOrComment> statements;
	}
}
