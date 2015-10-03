// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.PLI.Terminals.PLI_PunctuationChoice;
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
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_Expression extends PrecedenceChooser
{
	public Python_Expression()
	{
	}
	
	public Python_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
	
	@Override
	protected void establishChoices() 
	{
		// Order doesn't matter much
		super.addUnaryOperator(Python_Parens.class);
		super.addUnaryOperator(Python_Braces.class);
		super.addUnaryOperator(Python_Brackets.class);
		super.addUnaryOperator(Python_UnarySign.class);
		super.addUnaryOperator(Python_Not_Expression.class);
		super.addUnaryOperator(Python_OctalNumber.class);
		super.addUnaryOperator(Python_HexNumber.class);
		super.addUnaryOperator(Python_Number.class);
		super.addUnaryOperator(Python_Literals.class);
		super.addUnaryOperator(Python_BackQuotes.class);
		super.addUnaryOperator(Python_Function_Call.class);
		super.addUnaryOperator(Python_BuiltIn.class);
		super.addUnaryOperator(Python_Variable.class);
		super.addUnaryOperator(Python_Star_Expression.class);
		super.addUnaryOperator(Python_StarStar_Expression.class);
		super.addUnaryOperator(Python_Lambda_Expression.class);
		super.addUnaryOperator(Python_Yield.class);

		// Highest precedence first
		super.addBinaryOperator(Python_SubscriptExpression.class);
		super.addBinaryOperator(Python_Subfield.class);
		super.addBinaryOperator(Python_Power_Expression.class);
		super.addBinaryOperator(Python_Multiplicative_Expression.class);
		super.addBinaryOperator(Python_Additive_Expression.class);
		super.addBinaryOperator(Python_Shift_Expression.class);
		super.addBinaryOperator(Python_Bitwise_And_Expression.class);
		super.addBinaryOperator(Python_Bitwise_Xor_Expression.class);
		super.addBinaryOperator(Python_Bitwise_Or_Expression.class);
		super.addBinaryOperator(Python_Relational_Expression.class);
		super.addBinaryOperator(Python_And_Expression.class);
		super.addBinaryOperator(Python_Or_Expression.class);
		super.addBinaryOperator(Python_If_Else_Expression.class);
		super.addBinaryOperator(Python_For_In_Expression.class);
		super.addBinaryOperator(Python_Assignment_Expression.class);
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static class Python_Literals extends UnaryOperator
	{
		public TokenList<Python_Literal_or_Comment> literals;
		
		public static class Python_Literal_or_Comment extends TokenChooser
		{
			public Python_Literal literal;
			public Python_Comment comment;
		}
	}
	
	public static class Python_BackQuotes extends UnaryOperator
	{
		// These are obsolete as of Python 3.
		public @CURIOUS("Obsolete backquotes") TokenList<Python_BackQuote> quotes;
	}
	
	public static class Python_Variable_List extends UnaryOperator
	{
		public @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
		public Python_Variable var;
		public @OPT Python_Variable_Default defaultValue;
		public @OPT TokenList<Python_MoreVariablesInList> moreVars;

		public static class Python_MoreVariablesInList extends TokenSequence
		{
			public Python_Punctuation comma = new Python_Punctuation(',');
			public @OPT TokenList<Python_Comment> comments;
			public @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
			public Python_Variable var;
			public @OPT Python_Variable_Default defaultValue;
		}
		
		public static class Python_Variable_Default extends TokenSequence
		{
			public Python_Punctuation equals = new Python_Punctuation('=');
			public Python_Expression defaultValue;
		}
	}
	
	public static class Python_List extends UnaryOperator
	{
		public @OPT TokenList<Python_Comment> comment1;
		public @OPT Python_Expression expr;
		public @OPT TokenList<Python_MoreListItem> nextItem;
		public @OPT Python_Punctuation comma = new Python_Punctuation(',');
		public @OPT TokenList<Python_Comment> comment2;
		
		public static class Python_MoreListItem extends TokenSequence
		{
			public Python_Punctuation comma = new Python_Punctuation(',');
			public @OPT TokenList<Python_Comment> comment;
			public Python_Expression expr;
		}
	}
	
	public static class Python_Parens extends UnaryOperator
	{
		public Python_Punctuation leftParen = new Python_Punctuation('(');
		public @OPT TokenList<Python_Comment> comment;
		public @OPT Python_EndOfLine eoln;
		public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
		public Python_Punctuation rightParen = new Python_Punctuation(')');
	}
	
	public static class Python_Brackets extends UnaryOperator
	{
		public Python_Punctuation leftBracket = new Python_Punctuation('[');
		public @OPT TokenList<Python_Comment> comment;
		public @OPT Python_EndOfLine eoln;
		public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
		public Python_Punctuation rightBracket = new Python_Punctuation(']');
	}
	
	public static class Python_Braces extends UnaryOperator
	{
		public Python_Punctuation leftBrace = new Python_Punctuation('{');
		public @OPT Python_EndOfLine eoln;
		public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_Dictionary dictionary;
		public Python_Punctuation rightBrace = new Python_Punctuation('}');
		
		public static class Python_Dictionary extends TokenSequence
		{
			public @OPT TokenList<Python_Comment> comment1;
			public @OPT Python_DictionaryElement element;
			public @OPT TokenList<Python_MoreDictionaryElement> nextElement;
			public @OPT Python_Punctuation comma = new Python_Punctuation(',');
			public @OPT TokenList<Python_Comment> comment2;
			
			public static class Python_DictionaryElement extends TokenSequence
			{
				public Python_Expression key;
				public Python_Punctuation colon = new Python_Punctuation(':');
				public @OPT Python_EndOfLine eoln;
				public @OPT Python_Comment comment;
				public Python_Expression value;
			}
			
			public static class Python_MoreDictionaryElement extends TokenSequence
			{
				public Python_Punctuation comma = new Python_Punctuation(',');
				public @OPT TokenList<Python_Comment> comment;
				public Python_DictionaryElement element;
			}
		}
	}
	
	public static class Python_UnarySign extends UnaryOperator
	{
		public Python_PunctuationChoice sign = new Python_PunctuationChoice("*", "-", "+", "~");
		public Python_Expression expr;
	}

	public static class Python_Star_Expression extends UnaryOperator
	{
		public Python_Punctuation star = new Python_Punctuation('*');
		public Python_Expression expr;
	}

	public static class Python_StarStar_Expression extends UnaryOperator
	{
		public Python_Punctuation starStar = new Python_Punctuation("**");
		public Python_Expression expr;
	}

	public static class Python_BuiltIn extends TokenChooser
	{
		public Python_KeywordChoice builtins = new Python_KeywordChoice("False", "True");
	}
	
	public static class Python_Function_Call extends UnaryOperator
	{
		public Python_Variable name;
		public TokenList<Python_Function_Arguments> args;
		
		public static class Python_Function_Arguments extends TokenSequence
		{
			public Python_Punctuation leftParen = new Python_Punctuation('(');
			public @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_Function_ArgList argList;
			public Python_Punctuation rightParen = new Python_Punctuation(')');
			
			public static class Python_Function_ArgList extends TokenSequence
			{
				public @OPT Python_EndOfLine eoln;
				public @OPT TokenList<Python_Comment> comment1;
				public Python_Function_Params params;
				public @OPT Python_Punctuation comma = new Python_Punctuation(',');
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
					public Python_Punctuation equals = new Python_Punctuation('=');
					public Python_Expression expr;
				}
				
				public static class Python_MoreArgument extends TokenSequence
				{
					public Python_Punctuation comma = new Python_Punctuation(',');
					public @OPT TokenList<Python_Comment> comment;
					public @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
					public Python_Expression expr;
					public @OPT Python_InitialValue init;
				}
			}
		}
	}
	
	public static class Python_Lambda_Expression extends UnaryOperator 
	{
		public Python_Keyword LAMBDA = new Python_Keyword("lambda");
		public @OPT Python_Punctuation leftParen = new Python_Punctuation('(');
		public @OPT Python_Variable_List parameters;
		public @OPT Python_Punctuation rightParen = new Python_Punctuation(')');
		public Python_Punctuation colon = new Python_Punctuation(':');
		public Python_Expression expr;
	}
	
	public static class Python_Yield extends UnaryOperator
	{
		public Python_Keyword YIELD = new Python_Keyword("yield");
		public Python_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static class Python_Power_Expression extends BinaryOperator
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation stars = new Python_Punctuation("**");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Multiplicative_Expression extends BinaryOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_PunctuationChoice operator = new Python_PunctuationChoice("//", "*", "/", "%");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Additive_Expression extends BinaryOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_PunctuationChoice operator = new Python_PunctuationChoice("+", "-");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Shift_Expression extends BinaryOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_PunctuationChoice operator = new Python_PunctuationChoice("<<", ">>");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Bitwise_And_Expression extends BinaryOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation and = new Python_Punctuation('&');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Bitwise_Xor_Expression extends BinaryOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation xor = new Python_Punctuation('^');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Bitwise_Or_Expression extends BinaryOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation or = new Python_Punctuation('|');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Relational_Expression extends BinaryOperator 
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

	public static class Python_Not_Expression extends BinaryOperator 
	{
		public Python_Keyword NOT = new Python_Keyword("not");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_And_Expression extends BinaryOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword AND = new Python_Keyword("and");
		public @OPT TokenList<Python_Comment> comment;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Python_Or_Expression extends BinaryOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword OR = new Python_Keyword("or");
		public @OPT TokenList<Python_Comment> comment;
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Python_If_Else_Expression extends BinaryOperator 
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword IF = new Python_Keyword("if");
		public Python_Expression middle = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Keyword ELSE = new Python_Keyword("else");
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Python_For_In_Expression extends BinaryOperator
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
	
	public static class Python_Subfield extends BinaryOperator
	{
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation dot = new Python_Punctuation('.');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Python_SubscriptExpression extends BinaryOperator
	{
		public Python_Expression expr = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation leftBracket = new Python_Punctuation('[');
		public @OPT Python_EndOfLine eoln;
		public @SYNTAX(Python_Multiline_Syntax.class) Python_SubscrExpr subscr;
		public Python_Punctuation rightBracket = new Python_Punctuation(']');
		
		public static class Python_SubscrExpr extends TokenSequence
		{
			public @OPT Python_Expression subscr;
			public @OPT Python_ColonSubscript subscriptStop;
			public @OPT Python_ColonSubscript subscriptStep;
		}

		public static class Python_ColonSubscript extends TokenSequence
		{
			public Python_Punctuation colon = new Python_Punctuation(':');
			public @OPT Python_EndOfLine eoln;
			public @OPT Python_Expression expr;
		}
	}
	
	public static class Python_Assignment_Expression extends BinaryOperator
	{
		public Python_Expression leftVar = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Punctuation equals = new Python_Punctuation('=');
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
}
