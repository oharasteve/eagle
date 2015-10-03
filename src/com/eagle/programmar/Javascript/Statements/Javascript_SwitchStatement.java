// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Statement.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Javascript_SwitchStatement extends TokenSequence
{
	public @DOC("js_switch.asp") Javascript_Keyword SWITCH = new Javascript_Keyword("switch");
	public Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
	public @NOSPACE Javascript_Expression val;
	public @NOSPACE Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
	public @INDENT Javascript_Punctuation leftBrace = new Javascript_Punctuation('{');
	public TokenList<Javascript_CaseClause> caseClause;
	public @OPT Javascript_DefaultClause elseClause;
	public @OUTDENT Javascript_Punctuation rightBrace = new Javascript_Punctuation('}');
	
	public static class Javascript_CaseClause extends TokenSequence
	{
		public @NEWLINE2 Javascript_Keyword CASE = new Javascript_Keyword("case");
		public Javascript_Expression expr;
		public Javascript_Punctuation colon = new Javascript_Punctuation(':');
		public @OPT TokenList<Javascript_StatementOrComment> statements;
	}
	
	public static class Javascript_DefaultClause extends TokenSequence
	{
		public @NEWLINE Javascript_Keyword DEFAULT = new Javascript_Keyword("default");
		public Javascript_Punctuation colon = new Javascript_Punctuation(':');
		public TokenList<Javascript_StatementOrComment> statements;
	}
}
