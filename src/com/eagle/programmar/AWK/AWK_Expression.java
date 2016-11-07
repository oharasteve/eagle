// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.programmar.AWK.Terminals.AWK_Literal;
import com.eagle.programmar.AWK.Terminals.AWK_Number;
import com.eagle.programmar.AWK.Terminals.AWK_Pattern;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();
	
	public @P(10) AWK_Number number;
	
	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//
	
	public AWK_Expression()
	{
		super(_operators);
	}
	
	public AWK_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class AWK_FunctionCall extends PrimaryOperator
	{
		public AWK_KeywordChoice functionName = new AWK_KeywordChoice(AWK_Syntax.FUNCTIONS);
		public PunctuationLeftParen leftParen;
		public @OPT AWK_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}

	public static @P(110) class AWK_PatternExpression extends PrimaryOperator
	{
		public AWK_Pattern pattern;
	}

	public static @P(120) class AWK_PreIncrementExpression extends PrimaryOperator
	{
		public AWK_Punctuation operator = new AWK_Punctuation("++");
		public AWK_Expression expr;
	}

	public static @P(130) class AWK_PreDecrementExpression extends PrimaryOperator
	{
		public AWK_Punctuation operator = new AWK_Punctuation("--");
		public AWK_Expression expr;
	}
	
	public static @P(140) class AWK_PostIncrementExpression extends PrimaryOperator
	{
		public AWK_Variable var;		// Cannot be just AWK_Expression -- infinite loop
		public AWK_Punctuation operator = new AWK_Punctuation("++");
	}

	public static @P(150) class AWK_PostDecrementExpression extends PrimaryOperator
	{
		public AWK_Variable var;		// Cannot be just AWK_Expression -- infinite loop
		public AWK_Punctuation operator = new AWK_Punctuation("--");
	}

	public static @P(160) class AWK_Strings extends PrimaryOperator
	{
		public TokenList<AWK_StringPiece> pieces;
		
		public static class AWK_StringPiece extends TokenChooser
		{
			public @CHOICE AWK_Literal literal;
			public @CHOICE AWK_Identifier_Reference id;
		}
	}
	
	public static @P(170) class AWK_NotExpression extends PrimaryOperator
	{
		public AWK_Punctuation operator = new AWK_Punctuation('!');
		public AWK_Expression expr;
	}

	public static @P(180) class AWK_ParenthesizedExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public AWK_Expression expression;
		public PunctuationRightParen rightParen;
	}

	public static @P(190) class AWK_DollarParensExpression extends PrimaryOperator
	{
		public AWK_Punctuation dollar = new AWK_Punctuation('$');
		public PunctuationLeftParen leftParen;
		public AWK_Expression expression;
		public PunctuationRightParen rightParen;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static @P(200) class AWK_SubscriptExpression extends PrecedenceOperator
	{
		public AWK_Expression expr = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public AWK_Expression subscr = new AWK_Expression();
		public PunctuationRightBracket rightBracket;
	}
	
	public static @P(210) class AWK_MultiplicativeExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("*", "/", "%");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(220) class AWK_AdditiveExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("+", "-");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(230) class AWK_RelationalExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(240) class AWK_RegularExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice operator = new AWK_PunctuationChoice("~", "!~");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(250) class AWK_AndExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Punctuation andOperator = new AWK_Punctuation("&&");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(260) class AWK_OrExpression extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Punctuation orOperator = new AWK_Punctuation("||");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(270) class AWK_Assignment extends PrecedenceOperator
	{
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_PunctuationChoice equals = new AWK_PunctuationChoice("=", "+=");
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
}
