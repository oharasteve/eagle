// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodBody;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameters;
import com.eagle.programmar.CSharp.Terminals.CSharp_Character_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) CSharp_HexNumber hex;
	public @P(20) CSharp_Number number;
	public @P(30) CSharp_Literal literal;
	public @P(40) CSharp_Character_Literal characters;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public CSharp_Expression()
	{
	    super(_operators);
	}

	public CSharp_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class CSharp_DotClass extends PrimaryOperator
	{
		public CSharp_Type jtype;
		public PunctuationPeriod dot;
		public CSharp_Keyword CLASS = new CSharp_Keyword("class");
	}
	
	public static @P(110) class CSharp_CastExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public CSharp_Type jtype;
		public PunctuationRightParen rightParen;
		public CSharp_Expression expr;
	}

	public static @P(120) class CSharp_ExpressionList extends PrimaryOperator
	{
		public PunctuationLeftBrace leftBrace;
		public @OPT TokenList<CSharp_Comment> comment;
		public CSharp_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static @P(130) class CSharp_InterfaceCreationWithMethod extends PrimaryOperator
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_KeywordChoice jinterface = new CSharp_KeywordChoice(
				"Runnable", "ActionListener", "WindowAdapter");
		public PunctuationLeftParen leftParen;
		public PunctuationRightParen rightParen;
		public PunctuationLeftBrace leftBrace;
		public CSharp_Method method;
		public PunctuationRightBrace rightBrace;
	}

	public static @P(140) class CSharp_ClassCreationExpression extends PrimaryOperator
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_Type jtype;
		public PunctuationLeftParen leftParen;
		public @OPT TokenList<CSharp_Comment> comments;
		public @OPT CSharp_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}
	
	public static @P(150) class CSharp_ClassCreationWithInitializers extends PrimaryOperator
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_Type jtype;
		public PunctuationLeftBrace leftBrace;
		public @OPT CSharp_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static @P(160) class CSharp_ClassCreationWithSubscript extends PrimaryOperator
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_Type jtype;
		public TokenList<CSharp_Subscript> subscripts;
	}

	public static @P(170) class CSharp_MethodInvocation extends PrimaryOperator
	{
		public CSharp_Variable methodName;
		public PunctuationLeftParen leftParen;
		public @OPT CSharp_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}
	
	public static @P(180) class CSharp_PreIncrementExpression extends PrimaryOperator
	{
		public CSharp_Punctuation preIncrementOperator = new CSharp_Punctuation("++");
		public CSharp_Variable var;
	}

	public static @P(190) class CSharp_PreDecrementExpression extends PrimaryOperator
	{
		public CSharp_Punctuation preDecrementOperator = new CSharp_Punctuation("--");
		public CSharp_Variable var;
	}
	
	public static @P(200) class CSharp_PostIncrementExpression extends PrimaryOperator
	{
		public CSharp_Variable var;
		public CSharp_Punctuation postIncrementOperator = new CSharp_Punctuation("++");
	}

	public static @P(210) class CSharp_PostDecrementExpression extends PrimaryOperator
	{
		public CSharp_Variable var;
		public CSharp_Punctuation postDecrementOperator = new CSharp_Punctuation("--");
	}
	
	public static @P(220) class CSharp_NegativeExpression extends PrimaryOperator
	{
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("-", "+");
		public CSharp_Expression expr;
	}

	public static @P(230) class CSharp_LogicalNotExpression extends PrimaryOperator
	{
		public CSharp_Punctuation logicalNotOperator = new CSharp_Punctuation('~');
		public CSharp_Expression expr;
	}
	
	public static @P(240) class CSharp_NotExpression extends PrimaryOperator
	{
		public CSharp_Punctuation notOperator = new CSharp_Punctuation('!');
		public CSharp_Expression expr;
	}
	
	public static @P(250) class CSharp_BuiltIn extends PrimaryOperator
	{
		public CSharp_KeywordChoice builtIn = new CSharp_KeywordChoice("false", "true", "null", "this", "super");
	}
	
	public static @P(260) class CSharp_VariableExpression extends PrimaryOperator
	{
		public CSharp_Variable variable;
	}
	
	public static @P(270) class CSharp_TypeExpression extends PrimaryOperator
	{
		public CSharp_Type type;
	}
	
	public static @P(280) class CSharp_ParenthesizedExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public CSharp_Expression expression;
		public PunctuationRightParen rightParen;
	}
	
	public static @P(290) class CSharp_CommentExpression extends PrimaryOperator
	{
		public CSharp_Comment comment;
		public CSharp_Expression expr;
	}
	
	public static @P(300) class CSharp_TypeOf extends PrimaryOperator
	{
		public CSharp_Keyword TYPEOF = new CSharp_Keyword("typeof");
		public PunctuationLeftParen leftParen;
		public CSharp_Type type;
		public PunctuationRightParen rightParen;
	}

	public static @P(310) class CSharp_Delegation extends PrimaryOperator
	{
		public CSharp_Keyword DELEGATE = new CSharp_Keyword("delegate");
		public CSharp_MethodParameters parameters;
		public @NEWLINE CSharp_MethodBody body;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(320) class CSharp_SubscriptExpression extends PrecedenceOperator
	{
		public CSharp_Expression expr = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public CSharp_Expression subscr = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public PunctuationRightBracket rightBracket;
	}
	
	public static @P(330) class CSharp_NamespaceExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(340) class CSharp_SubfieldExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(350) class CSharp_MultiplicativeExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("*", "/", "%");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(360) class CSharp_AdditiveExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("+", "-");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(370) class CSharp_ShiftExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("<<", ">>", ">>>");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(380) class CSharp_RelationalExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("<", ">", "<=", ">=");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(390) class CSharp_InstanceOfExpression extends PrecedenceOperator
	{
		public CSharp_Expression expr = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Keyword instanceOperator = new CSharp_Keyword("is");
		public CSharp_Type type;
	}

	public static @P(400) class CSharp_EqualityExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_EqualityOperator equalityOperator;
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

		public static class CSharp_EqualityOperator extends TokenChooser
		{
			public @CHOICE CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("==", "!=", "??");
			public @CHOICE CSharp_KeywordChoice asIs = new CSharp_KeywordChoice("as", "is");
		}
	}

	public static @P(410) class CSharp_AndExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation bitwiseAndOperator = new CSharp_Punctuation('&');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class CSharp_ExclusiveOrExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation bitwiseXOrOperator = new CSharp_Punctuation('^');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(430) class CSharp_InclusiveOrExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation bitwiseOrOperator = new CSharp_Punctuation('|');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(440) class CSharp_ConditionalAndExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation andOperator = new CSharp_Punctuation("&&");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(450) class CSharp_ConditionalOrExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation orOperator = new CSharp_Punctuation("||");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(460) class CSharp_TrueFalseExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Punctuation questionMark = new CSharp_Punctuation('?');
		public CSharp_Expression middle = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationColon colon;
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static @P(470) class CSharp_AssignmentExpression extends PrecedenceOperator
	{
		public CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public CSharp_Expression expr;
	}

	public static @P(480) class CSharp_LambdaExpression extends PrecedenceOperator
	{
		public CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Punctuation lambda = new CSharp_Punctuation("=>");
		public CSharp_Expression expr;
	}
}
