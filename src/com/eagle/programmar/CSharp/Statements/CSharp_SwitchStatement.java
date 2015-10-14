// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement.CSharp_StatementBlock.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_SwitchStatement extends TokenSequence
{
	public @NEWLINE @DOC("statements.html#14.11") CSharp_Keyword SWITCH = new CSharp_Keyword("switch");
	public PunctuationLeftParen leftParen;
	public CSharp_Expression val;
	public PunctuationRightParen rightParen;
	public @INDENT PunctuationLeftBrace leftBrace;
	public TokenList<CSharp_CaseClause> caseClause;
	public @OUTDENT PunctuationRightBrace rightBrace;
	
	public static class CSharp_CaseClause extends TokenSequence
	{
		public @NEWLINE CSharp_CaseType caseType;
		public PunctuationColon colon;
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
