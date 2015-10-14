// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.Lisp.Functions;

import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Lisp_Variable;
import com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_LoopFunction extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public @DOC("m_loop.htm") Lisp_Keyword LOOP = new Lisp_Keyword("loop");
	public Lisp_LoopType loopType;
	public PunctuationRightParen rightParen;
	
	public static class Lisp_LoopType extends TokenChooser
	{
		public Lisp_SExpr simpleExpr;
		
		public static class Lisp_LoopFancy extends TokenSequence
		{
			public @OPT Lisp_LoopNamed named;
			public @OPT TokenList<Lisp_LoopVariableClause> variableClauses;
			public TokenList<Lisp_LoopMainClause> mainClauses;
			
			public static class Lisp_LoopNamed extends TokenSequence
			{
				public Lisp_Keyword NAMED = new Lisp_Keyword("named");
				public Lisp_Variable_Definition name;
			}
			
			public static class Lisp_LoopVariableClause extends TokenChooser
			{
				public Lisp_LoopInitialFinal initialFinal;
		
				public static class Lisp_LoopWith extends TokenSequence
				{
					public Lisp_LoopWithElement element;
					public @OPT TokenList<Lisp_LoopMoreWith> more;
					
					public static class Lisp_LoopMoreWith extends TokenSequence
					{
						public Lisp_Keyword AND = new Lisp_Keyword("and");
						public Lisp_LoopWithElement element;
					}
					
					public static class Lisp_LoopWithElement extends TokenSequence
					{
						public Lisp_Keyword WITH = new Lisp_Keyword("with");
						public @OPT Lisp_SExpr typeSpec;
						public @OPT Lisp_LoopWithValue equalsValue;
						
						public static class Lisp_LoopWithValue extends TokenSequence
						{
							public PunctuationEquals equals;
							public Lisp_SExpr value;
						}
					}
				}
				
				public static class Lisp_LoopForAsClause extends TokenSequence
				{
					public Lisp_KeywordChoice FOR = new Lisp_KeywordChoice("for", "as");
					public Lisp_Variable var;
					public TokenList<Lisp_LoopForClause> forClause;
					
					public static class Lisp_LoopForClause extends TokenChooser
					{
						public static class Lisp_ForArithmetic extends TokenSequence
						{
							public Lisp_KeywordChoice direction = new Lisp_KeywordChoice(
									"across", "below", "from", "in", "on", "to");
							public Lisp_SExpr expr;
						}
		
						public static class Lisp_ForEqualsThen extends TokenSequence
						{
							public PunctuationEquals equals;
							public Lisp_SExpr expr;
							public @OPT Lisp_LoopForThen thenClause;
							
							public static class Lisp_LoopForThen extends TokenSequence
							{
								public Lisp_Keyword THEN = new Lisp_Keyword("then");
								public Lisp_SExpr expr;
							}
						}
					}
				}
			}
			
			public static class Lisp_LoopMainClause extends TokenChooser
			{
				public Lisp_LoopInitialFinal initialFinal;
				
				public static class Lisp_LoopUnconditionalDo extends TokenSequence
				{
					public Lisp_KeywordChoice DO = new Lisp_KeywordChoice("do", "doing");
					public TokenList<Lisp_SExpr> actions;
				}
				
				public static class Lisp_LoopUnconditionalReturn extends TokenSequence
				{
					public Lisp_Keyword RETURN = new Lisp_Keyword("return");
					public Lisp_SExpr value;
				}
				
				public static class Lisp_LoopListAccumulation extends TokenSequence
				{
					public Lisp_KeywordChoice operation = new Lisp_KeywordChoice(
							"append",
							"appenging",
							"collect",
							"collecting",
							"nconc",
							"nconcing");
					public Lisp_SExpr expr;
				}
				
				public static class Lisp_LoopNumericAccumulation extends TokenSequence
				{
					public Lisp_KeywordChoice operation = new Lisp_KeywordChoice(
							"count",
							"counting",
							"maximize",
							"maximizing",
							"minimize",
							"minimizing",
							"sum",
							"summing");
					public Lisp_SExpr value;
					public @OPT Lisp_LoopAccumulateInto accumulateInto;
					
					public static class Lisp_LoopAccumulateInto extends TokenSequence
					{
						public Lisp_Keyword INTO = new Lisp_Keyword("into");
						public Lisp_Variable variable;
					}
				}
		
				public static class Lisp_LoopConditional extends TokenSequence
				{
					public Lisp_KeywordChoice when = new Lisp_KeywordChoice("if", "when", "unless");
					public Lisp_SExpr value;
					public @OPT TokenList<Lisp_LoopMoreSelectableClause> more;
					public @OPT Lisp_LoopConditionalElse conditionalElse;
					public @OPT Lisp_Keyword END = new Lisp_Keyword("end");
					
					public static class Lisp_LoopMoreSelectableClause extends TokenSequence
					{
						public Lisp_Keyword AND = new Lisp_Keyword("and");
						public Lisp_SExpr value;
					}
					
					public static class Lisp_LoopConditionalElse extends TokenSequence
					{
						public Lisp_Keyword ELSE = new Lisp_Keyword("else");
						public Lisp_SExpr clause;
						public @OPT TokenList<Lisp_LoopMoreSelectableClause> more;
					}
				}
		
				public static class Lisp_LoopTerminationTest extends TokenSequence
				{
					public Lisp_KeywordChoice when = new Lisp_KeywordChoice(
							"while", "until", "repeat", "always", "never", "thereis");
					public Lisp_SExpr condition;
				}
			}
			
			public static class Lisp_LoopInitialFinal extends TokenSequence
			{
				public Lisp_KeywordChoice when = new Lisp_KeywordChoice("initially", "finally");
				public Lisp_SExpr expr;
			}
		}
	}
}
