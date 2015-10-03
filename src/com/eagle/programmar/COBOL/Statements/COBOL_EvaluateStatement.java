// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Sentence.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_EvaluateStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpseval.htm") COBOL_Keyword EVALUATE = new COBOL_Keyword("EVALUATE");
	public COBOL_EvaluateWhat key;
	public @OPT TokenList<COBOL_Comment> comments;
	public @OPT TokenList<COBOL_EvaluateAlsoClause> alsos;
	public TokenList<COBOL_EvaluateWhenClause> whens;
	public @OPT COBOL_Keyword ENDEVALUATE = new COBOL_Keyword("END-EVALUATE");
	
	public static class COBOL_EvaluateWhat extends TokenChooser
	{
		public COBOL_Identifier_Reference id;
		public @FIRST COBOL_Keyword OTHER = new COBOL_Keyword("OTHER");
		
		// Careful -- Condition has to precede Expression here.
		public static class COBOL_EvaluateCondition extends TokenSequence
		{
			public COBOL_Expression cond;
		}

		public static class COBOL_EvaluateExpression extends TokenSequence
		{
			public COBOL_Expression expr;
			public @OPT COBOL_EvaluateThru thru;
			
			public static class COBOL_EvaluateThru extends TokenSequence
			{
				public COBOL_Keyword THRU = new COBOL_Keyword("THRU");
				public COBOL_Expression expr;
			}
		}
	}

	public static class COBOL_EvaluateAlsoClause extends TokenSequence
	{
		public COBOL_Keyword ALSO = new COBOL_Keyword("ALSO");
		public COBOL_EvaluateWhat value;
	}

	public static class COBOL_EvaluateWhenClause extends TokenSequence
	{
		public COBOL_Keyword WHEN = new COBOL_Keyword("WHEN");
		public COBOL_EvaluateWhat value;
		public @OPT TokenList<COBOL_EvaluateAlsoClause> alsos;
		public @OPT TokenList<COBOL_StatementOrComment> statements; 
	}
}
