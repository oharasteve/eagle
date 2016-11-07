// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.programmar.VB.Terminals.VB_Literal;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.programmar.VB.Terminals.VB_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class VB_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) VB_Number number;
	public @P(20) VB_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public VB_Expression()
	{
	    super(_operators);
	}

	public VB_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class VB_BuiltIn extends PrimaryOperator
	{
		public VB_KeywordChoice builtIn = new VB_KeywordChoice("false", "true", "nothing");
	}
	
	public static @P(110) class VB_FunctionCall extends PrimaryOperator
	{
		public VB_Identifier_Reference fnName;
		public PunctuationLeftParen leftParen;
		public SeparatedList<VB_Expression,PunctuationComma> args;
		public PunctuationRightParen rightParen;
	}

	public static @P(120) class VB_NegativeExpression extends PrimaryOperator
	{
		public VB_PunctuationChoice operator = new VB_PunctuationChoice("-", "+");
		public VB_Expression expr;
	}

	public static @P(130) class VB_NotExpression extends PrimaryOperator
	{
		public VB_Keyword NOT = new VB_Keyword("NOT");
		public VB_Expression expr;
	}
	
	public static @P(140) class VB_VariableExpression extends PrimaryOperator
	{
		public VB_Variable variable;
	}
	
	public static @P(150) class VB_ParenthesizedExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public VB_Expression expression;
		public PunctuationRightParen rightParen;
	}
	
	public static @P(160) class VB_CommentExpression extends PrimaryOperator
	{
		public VB_Comment comment;
		public VB_Expression expr;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(170) class VB_SubscriptExpression extends PrecedenceOperator
	{
		public VB_Expression expr = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public VB_Expression subscr = new VB_Expression(this, AllowedPrecedence.HIGHER);
		public PunctuationRightBracket rightBracket;
	}

	public static @P(180) class VB_Subfield extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(190) class VB_ExponentExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation operator = new VB_Punctuation('^');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(200) class VB_MultiplicativeExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_MultiplyOperation operator;
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

		public static class VB_MultiplyOperation extends TokenChooser
		{
			public @CHOICE VB_Keyword MOD = new VB_Keyword("mod");
			public @CHOICE VB_PunctuationChoice op = new VB_PunctuationChoice("*", "/", "%", "\\");
		}
	}

	public static @P(210) class VB_AdditiveExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_PunctuationChoice operator = new VB_PunctuationChoice("+", "-");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(220) class VB_ConcatExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation ampersand = new VB_Punctuation('&');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(230) class VB_ShiftExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_PunctuationChoice operator = new VB_PunctuationChoice("<<", ">>");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(240) class VB_RelationalExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_PunctuationChoice operator = new VB_PunctuationChoice(
				"<=", ">=", "<>", "<", ">");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(250) class VB_InstanceOfExpression extends PrecedenceOperator
	{
		public VB_Expression expr = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Keyword instanceOperator = new VB_Keyword("instanceof");
		public VB_Type type;
	}

	public static @P(260) class VB_EqualityExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_EqualityOperator equalityOperator;
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

		public static class VB_EqualityOperator extends TokenChooser
		{
			public @CHOICE PunctuationEquals equals;
			public @CHOICE VB_KeywordChoice IS = new VB_KeywordChoice("is", "like", "isnot");
		}
	}

	public static @P(270) class VB_AndExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation bitwiseAndOperator = new VB_Punctuation('&');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(280) class VB_ExclusiveOrExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation bitwiseXOrOperator = new VB_Punctuation('^');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(290) class VB_InclusiveOrExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Keyword xor = new VB_Keyword("xor");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(300) class VB_ConditionalAndExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_KeywordChoice andOperator = new VB_KeywordChoice("and", "andalso");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(310) class VB_ConditionalOrExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_KeywordChoice orOperator = new VB_KeywordChoice("or", "orelse");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}
}
