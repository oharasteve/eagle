// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python;

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
	private static OperatorList _operators = new OperatorList();

	public @P(10) Python_OctalNumber octal;
	public @P(20) Python_HexNumber hex;
	public @P(30) Python_Number number;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Python_Expression()
	{
	    super(_operators);
	}

	public Python_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class Python_Funny_Constructor extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen1;
		public Python_Type type;
		public PunctuationStar star;
		public Python_Expression size;
		public PunctuationRightParen rightParen1;
		public PunctuationLeftParen leftParen2;
		public PunctuationRightParen rightParen2;
	}
	
	public static @P(110) class Python_Parens extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public @OPT @SYNTAX(Python_Multiline_Syntax.class) TokenList<Python_CommentEoln> comments;
		public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
		public PunctuationRightParen rightParen;
		
		public static class Python_CommentEoln extends TokenSequence
		{
			public Python_Comment comment;
			public @OPT Python_EndOfLine eoln;
		}
	}
	
	public static @P(120) class Python_Braces extends PrimaryOperator
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
	
	public static @P(130) class Python_Brackets extends PrimaryOperator
	{
		public PunctuationLeftBracket leftBracket;
		public @OPT TokenList<Python_Comment> comment;
		public @OPT Python_EndOfLine eoln;
		public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
		public PunctuationRightBracket rightBracket;
	}
	
	public static @P(140) class Python_UnarySign extends PrimaryOperator
	{
		public Python_PunctuationChoice sign = new Python_PunctuationChoice("*", "-", "+", "~");
		public Python_Expression expr;
	}

	public static @P(150) class Python_Not_Expression extends PrimaryOperator 
	{
		public Python_Keyword NOT = new Python_Keyword("not");
		public Python_Expression expr;
	}
	
	public static @P(160) class Python_Literals extends PrimaryOperator
	{
		public TokenList<Python_Literal> literals;
	}
	
	public static @P(170) class Python_BackQuotes extends PrimaryOperator
	{
		// These are obsolete as of Python 3.
		public @CURIOUS("Obsolete backquotes") TokenList<Python_BackQuote> quotes;
	}
	
	public static @P(180) class Python_Function_Call extends PrimaryOperator
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

	public static @P(190) class Python_BuiltIn extends PrimaryOperator
	{
		public Python_KeywordChoice builtins = new Python_KeywordChoice("False", "True");
	}
	
	public static @P(200) class Python_VariableExpression extends PrimaryOperator
	{
		public Python_Variable variable;
	}
	
	public static @P(210) class Python_Star_Expression extends PrimaryOperator
	{
		public PunctuationStar star;
		public Python_Expression expr;
	}

	public static @P(220) class Python_StarStar_Expression extends PrimaryOperator
	{
		public Python_Punctuation starStar = new Python_Punctuation("**");
		public Python_Expression expr;
	}
	
	public static @P(230) class Python_Lambda_Expression extends PrimaryOperator 
	{
		public Python_Keyword LAMBDA = new Python_Keyword("lambda");
		public @OPT PunctuationLeftParen leftParen;
		public @OPT Python_Variable_List parameters;
		public @OPT PunctuationRightParen rightParen;
		public PunctuationColon colon;
		public Python_Expression expr;
		
		public static class Python_Variable_List extends TokenSequence
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
		
	}
	
	public static @P(240) class Python_Yield extends PrimaryOperator
	{
		public Python_Keyword YIELD = new Python_Keyword("yield");
		public Python_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(250) class Python_SubscriptExpression extends PrecedenceOperator
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
	
	public static @P(260) class Python_Subfield extends PrecedenceOperator
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(270) class Python_Power_Expression extends PrecedenceOperator
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation stars = new Python_Punctuation("**");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(280) class Python_Multiplicative_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_PunctuationChoice operator = new Python_PunctuationChoice("//", "*", "/", "%");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(290) class Python_Additive_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_PunctuationChoice operator = new Python_PunctuationChoice("+", "-");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(300) class Python_Shift_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_PunctuationChoice operator = new Python_PunctuationChoice("<<", ">>");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(310) class Python_Bitwise_And_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation and = new Python_Punctuation('&');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(320) class Python_Bitwise_Xor_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation xor = new Python_Punctuation('^');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(330) class Python_Bitwise_Or_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation or = new Python_Punctuation('|');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(340) class Python_Relational_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Relational_Operator relOp;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

		public static class Python_Relational_Operator extends TokenChooser
		{
			public @CHOICE Python_PunctuationChoice operator = new Python_PunctuationChoice(
					"==", "!=", "<>", "<=", ">=", "<", ">");
			
			public @CHOICE static class Python_IN_Operator extends TokenSequence
			{
				public @OPT Python_Keyword NOT = new Python_Keyword("not");
				public Python_Keyword IN = new Python_Keyword("in");
			}
			
			public @CHOICE static class Python_IS_Operator extends TokenSequence
			{
				public Python_Keyword IS = new Python_Keyword("is");
				public @OPT Python_Keyword NOT = new Python_Keyword("not");
			}
		}
	}

	public static @P(350) class Python_And_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword AND = new Python_Keyword("and");
		public @OPT TokenList<Python_Comment> comment;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(360) class Python_Or_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword OR = new Python_Keyword("or");
		public @OPT TokenList<Python_Comment> comment;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(370) class Python_For_In_Expression extends PrecedenceOperator
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword FOR = new Python_Keyword("for");
		public Python_VariableList varList;
		public Python_Keyword IN = new Python_Keyword("in");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(380) class Python_If_Else_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword IF = new Python_Keyword("if");
		public Python_Expression middle = new Python_Expression(this, AllowedPrecedence.HIGHER);
		public Python_Keyword ELSE = new Python_Keyword("else");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(390) class Python_If_Expression extends PrecedenceOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword IF = new Python_Keyword("if");
		public Python_Expression middle = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(400) class Python_Assignment_Expression extends PrecedenceOperator
	{
		public Python_Expression leftVar = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationEquals equals;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
}
