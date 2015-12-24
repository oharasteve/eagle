// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.PLI.Terminals.PLI_PunctuationChoice;
import com.eagle.programmar.Python.Python_Expression.Python_Function_Call.Python_Function_Arguments;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_BackQuote;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_HexNumber;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.programmar.Python.Terminals.Python_OctalNumber;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Python_Expression extends PrecedenceChooser
{
	public Python_Expression()
	{
	}
	
	public Python_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
	
	@Override
	protected void establishChoices() 
	{
		// Order doesn't matter much
		super.addTerm(Python_Parens.class);
		super.addTerm(Python_Braces.class);
		super.addTerm(Python_Brackets.class);
		super.addTerm(Python_UnarySign.class);
		super.addTerm(Python_Not_Expression.class);
		super.addTerm(Python_OctalNumber.class);
		super.addTerm(Python_HexNumber.class);
		super.addTerm(Python_Number.class);
		super.addTerm(Python_Literals.class);
		super.addTerm(Python_BackQuotes.class);
		super.addTerm(Python_Function_Call.class);
		super.addTerm(Python_BuiltIn.class);
		super.addTerm(Python_Variable.class);
		super.addTerm(Python_Star_Expression.class);
		super.addTerm(Python_StarStar_Expression.class);
		super.addTerm(Python_Lambda_Expression.class);
		super.addTerm(Python_Yield.class);

		// Highest precedence first
		super.addOperator(Python_SubscriptExpression.class);
		super.addOperator(Python_Subfield.class);
		super.addOperator(Python_Power_Expression.class);
		super.addOperator(Python_Multiplicative_Expression.class);
		super.addOperator(Python_Additive_Expression.class);
		super.addOperator(Python_Shift_Expression.class);
		super.addOperator(Python_Bitwise_And_Expression.class);
		super.addOperator(Python_Bitwise_Xor_Expression.class);
		super.addOperator(Python_Bitwise_Or_Expression.class);
		super.addOperator(Python_Relational_Expression.class);
		super.addOperator(Python_And_Expression.class);
		super.addOperator(Python_Or_Expression.class);
		super.addOperator(Python_If_Else_Expression.class);
		super.addOperator(Python_For_In_Expression.class);
		super.addOperator(Python_Assignment_Expression.class);
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static class Python_Literals extends ExpressionTerm
	{
		public TokenList<Python_Literal> literals;
	}
	
	public static class Python_BackQuotes extends ExpressionTerm
	{
		// These are obsolete as of Python 3.
		public @CURIOUS("Obsolete backquotes") TokenList<Python_BackQuote> quotes;
	}
	
	public static class Python_Variable_List extends ExpressionTerm
	{
		public @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
		public Python_Variable var;
		public @OPT Python_Variable_Default defaultValue;
		public @OPT TokenList<Python_MoreVariablesInList> moreVars;

		public static class Python_MoreVariablesInList extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT TokenList<Python_Comment> comments;
			public @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
			public Python_Variable var;
			public @OPT Python_Variable_Default defaultValue;
		}
		
		public static class Python_Variable_Default extends TokenSequence
		{
			public PunctuationEquals equals;
			public Python_Expression defaultValue;
		}
	}
	
	public static class Python_List extends ExpressionTerm
	{
		public @OPT TokenList<Python_Comment> comment1;
		public @OPT Python_Expression expr;
		public @OPT TokenList<Python_MoreListItem> nextItem;
		public @OPT PunctuationComma comma;
		public @OPT TokenList<Python_Comment> comment2;
		
		public static class Python_MoreListItem extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT TokenList<Python_Comment> comment;
			public Python_Expression expr;
		}
	}
	
	public static class Python_Parens extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public @OPT TokenList<Python_Comment> comment;
		public @OPT Python_EndOfLine eoln;
		public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
		public PunctuationRightParen rightParen;
	}
	
	public static class Python_Brackets extends ExpressionTerm
	{
		public PunctuationLeftBracket leftBracket;
		public @OPT TokenList<Python_Comment> comment;
		public @OPT Python_EndOfLine eoln;
		public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
		public PunctuationRightBracket rightBracket;
	}
	
	public static class Python_Braces extends ExpressionTerm
	{
		public PunctuationLeftBrace leftBrace;
		public @OPT Python_EndOfLine eoln1;
		public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_Dictionary dictionary;
		public @OPT Python_EndOfLine eoln2;
		public PunctuationRightBrace rightBrace;
		
		public static class Python_Dictionary extends TokenSequence
		{
			public @OPT TokenList<Python_Comment> comment1;
			public @OPT Python_DictionaryElement element;
			public @OPT TokenList<Python_MoreDictionaryElement> nextElement;
			public @OPT PunctuationComma comma;
			public @OPT TokenList<Python_Comment> comment2;
			
			public static class Python_DictionaryElement extends TokenSequence
			{
				public Python_Expression key;
				public PunctuationColon colon;
				public @OPT Python_EndOfLine eoln;
				public @OPT Python_Comment comment;
				public Python_Expression value;
			}
			
			public static class Python_MoreDictionaryElement extends TokenSequence
			{
				public PunctuationComma comma;
				public @OPT TokenList<Python_Comment> comment;
				public Python_DictionaryElement element;
			}
		}
	}
	
	public static class Python_UnarySign extends ExpressionTerm
	{
		public Python_PunctuationChoice sign = new Python_PunctuationChoice("*", "-", "+", "~");
		public Python_Expression expr;
	}

	public static class Python_Star_Expression extends ExpressionTerm
	{
		public PunctuationStar star;
		public Python_Expression expr;
	}

	public static class Python_StarStar_Expression extends ExpressionTerm
	{
		public Python_Punctuation starStar = new Python_Punctuation("**");
		public Python_Expression expr;
	}

	public static class Python_BuiltIn extends TokenChooser
	{
		public Python_KeywordChoice builtins = new Python_KeywordChoice("False", "True");
	}
	
	public static class Python_Function_Call extends ExpressionTerm
	{
		public Python_Variable name;
		public TokenList<Python_Function_Arguments> args;
		public @OPT Python_Function_Arguments moreArguments;
		
		public static class Python_Function_Arguments extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_Function_ArgList argList;
			public @OPT Python_EndOfLine eoln;
			public PunctuationRightParen rightParen;
			
			public static class Python_Function_ArgList extends TokenSequence
			{
				public @OPT Python_EndOfLine eoln;
				public @OPT TokenList<Python_Comment> comment1;
				public Python_Function_Params params;
				public @OPT PunctuationComma comma;
				public @OPT TokenList<Python_Comment> comment2;
			}
			
			public static class Python_Function_Params extends TokenSequence
			{
				public @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
				public Python_Expression expr;
				public @OPT Python_InitialValue init;
				public @OPT TokenList<Python_MoreArgument> moreArgs;

				public static class Python_InitialValue extends TokenSequence
				{
					public PunctuationEquals equals;
					public Python_Expression expr;
				}
				
				public static class Python_MoreArgument extends TokenSequence
				{
					public PunctuationComma comma;
					public @OPT TokenList<Python_Comment> comment;
					public @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
					public Python_Expression expr;
					public @OPT Python_InitialValue init;
				}
			}
		}
	}
	
	public static class Python_Lambda_Expression extends ExpressionTerm 
	{
		public Python_Keyword LAMBDA = new Python_Keyword("lambda");
		public @OPT PunctuationLeftParen leftParen;
		public @OPT Python_Variable_List parameters;
		public @OPT PunctuationRightParen rightParen;
		public PunctuationColon colon;
		public Python_Expression expr;
	}
	
	public static class Python_Yield extends ExpressionTerm
	{
		public Python_Keyword YIELD = new Python_Keyword("yield");
		public Python_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static class Python_Power_Expression extends PrecedenceOperator
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation stars = new Python_Punctuation("**");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Multiplicative_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_PunctuationChoice operator = new Python_PunctuationChoice("//", "*", "/", "%");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Additive_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_PunctuationChoice operator = new Python_PunctuationChoice("+", "-");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Shift_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_PunctuationChoice operator = new Python_PunctuationChoice("<<", ">>");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Bitwise_And_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation and = new Python_Punctuation('&');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Bitwise_Xor_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation xor = new Python_Punctuation('^');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Bitwise_Or_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation or = new Python_Punctuation('|');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Relational_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Relational_Operator relOp;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

		public static class Python_Relational_Operator extends TokenChooser
		{
			public PLI_PunctuationChoice operator = new PLI_PunctuationChoice(
					"==", "!=", "<>", "<=", ">=", "<", ">");
			
			public static class Python_IN_Operator extends TokenSequence
			{
				public @OPT Python_Keyword NOT = new Python_Keyword("not");
				public Python_Keyword IN = new Python_Keyword("in");
			}
			
			public static class Python_IS_Operator extends TokenSequence
			{
				public Python_Keyword IS = new Python_Keyword("is");
				public @OPT Python_Keyword NOT = new Python_Keyword("not");
			}
		}
	}

	public static class Python_Not_Expression extends PrecedenceOperator 
	{
		public Python_Keyword NOT = new Python_Keyword("not");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_And_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword AND = new Python_Keyword("and");
		public @OPT TokenList<Python_Comment> comment;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Or_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword OR = new Python_Keyword("or");
		public @OPT TokenList<Python_Comment> comment;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Python_If_Else_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword IF = new Python_Keyword("if");
		public Python_Expression middle = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword ELSE = new Python_Keyword("else");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Python_For_In_Expression extends PrecedenceOperator
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword FOR = new Python_Keyword("for");
		public Python_VariableList varList;
		public Python_Keyword IN = new Python_Keyword("in");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
		public @OPT Python_For_In_If_Expression ifExpression;
		
		public static class Python_For_In_If_Expression extends TokenSequence
		{
			public Python_Keyword IF = new Python_Keyword("if");
			public Python_Expression condition = new Python_Expression();
		}
	}
	
	public static class Python_Subfield extends PrecedenceOperator
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Python_SubscriptExpression extends PrecedenceOperator
	{
		public Python_Expression expr = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public @OPT Python_EndOfLine eoln;
		public @SYNTAX(Python_Multiline_Syntax.class) Python_SubscrExpr subscr;
		public PunctuationRightBracket rightBracket;
		public @OPT Python_Function_Arguments moreArguments;
		
		public static class Python_SubscrExpr extends TokenSequence
		{
			public @OPT Python_Expression subscr;
			public @OPT Python_ColonSubscript subscriptStop;
			public @OPT Python_ColonSubscript subscriptStep;
		}

		public static class Python_ColonSubscript extends TokenSequence
		{
			public PunctuationColon colon;
			public @OPT Python_EndOfLine eoln;
			public @OPT Python_Expression expr;
		}
	}
	
	public static class Python_Assignment_Expression extends PrecedenceOperator
	{
		public Python_Expression leftVar = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationEquals equals;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
}
