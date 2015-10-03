// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement.CSharp_StatementBlock.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_SwitchStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.11") CSharp_Keyword SWITCH = new CSharp_Keyword("switch");
	public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
	public CSharp_Expression val;
	public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	public @INDENT CSharp_Punctuation leftBrace = new CSharp_Punctuation('{');
	public TokenList<CSharp_CaseClause> caseClause;
	public @OUTDENT CSharp_Punctuation rightBrace = new CSharp_Punctuation('}');
	
	public static class CSharp_CaseClause extends TokenSequence
	{
		public @NEWLINE CSharp_CaseType caseType;
		public CSharp_Punctuation colon = new CSharp_Punctuation(':');
		public @OPT TokenList<CSharp_StatementOrComment> statements;
	}
	
	public static class CSharp_CaseType extends TokenChooser
	{
		public CSharp_Keyword DEFAULT = new CSharp_Keyword("default");

		public static class CSharp_CaseClause extends TokenSequence
		{
			public @NEWLINE2 CSharp_Keyword CASE = new CSharp_Keyword("case");
			public CSharp_Expression expr;
		}
	}
}
