// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice;
import com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Gupta_Condition extends TokenSequence
{
	public Gupta_ConditionClause conditionClause;
	public @OPT TokenList<Gupta_AndOr> clauses;
	
	public static class Gupta_AndOr extends TokenSequence
	{
		public Gupta_KeywordChoice andOr = new Gupta_KeywordChoice("AND", "OR");
		public Gupta_ConditionClause conditionClause;
	}

	public static class Gupta_ConditionClause extends TokenSequence
	{
		public Gupta_Expression expr;
		public Gupta_OperatorExpression opExpr;
		
		public static class Gupta_OperatorExpression extends TokenSequence
		{
			public Gupta_Relational_Operator operator;
			public Gupta_Expression expr;
			
			public static class Gupta_Relational_Operator extends TokenChooser
			{
				public Gupta_PunctuationChoice operator = new Gupta_PunctuationChoice(">=", "<=", "<", ">", "=");
				public Gupta_KeywordChoice GT = new Gupta_KeywordChoice("GT", "LT", "NE");
				
				public static class Gupta_Not_Equals extends TokenSequence
				{
					public Gupta_Keyword NOT = new Gupta_Keyword("NOT");
					public PunctuationEquals equals;
				}

				public static class Gupta_Less_Than extends TokenSequence
				{
					public Gupta_Keyword LESS = new Gupta_Keyword("LESS");
					public Gupta_Keyword THAN = new Gupta_Keyword("THAN");
				}
				
				public static class Gupta_Greater_Than extends TokenSequence
				{
					public Gupta_Keyword GREATER = new Gupta_Keyword("GREATER");
					public Gupta_Keyword THAN = new Gupta_Keyword("THAN");
				}
			}
		}
	}
}
