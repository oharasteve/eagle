// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.Terminals.C_Character_Literal;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_HexNumber;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) C_Number number;
	public @P(20) C_HexNumber hex;
	public @P(30) C_Character_Literal characters;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public C_Expression()
	{
	    super(_operators);
	}

	public C_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class C_Literals extends PrimaryOperator
	{
		public TokenList<C_Literal> literals;
	}
	
	public static @P(110) class C_CastExpression extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen;
		public C_Type ctype;
		public PunctuationRightParen rightParen;
		public C_Expression expr;
	}

	public static @P(120) class C_ExpressionList extends PrimaryOperator
	{
		public PunctuationLeftBrace leftBrace;
		public C_ArgumentList valueList;
		public @OPT C_Comment comment;
		public PunctuationRightBrace rightBrace;
	}
	
	public static @P(130) class C_FunctionCall extends PrimaryOperator
	{
		public C_Variable functionName;
		public PunctuationLeftParen leftParen;
		public @OPT C_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}
				
	public static @P(140) class C_FunctionPointerCall extends PrimaryOperator
	{
		public PunctuationLeftParen leftParen1;
		public @OPT PunctuationStar star2;
		public C_Variable methodName;
		public PunctuationRightParen rightParen1;
		public PunctuationLeftParen leftParen2;
		public @OPT C_ArgumentList argList;
		public PunctuationRightParen rightParen2;
	}

	public static @P(150) class C_PreIncrementExpression extends PrimaryOperator
	{
		public C_Punctuation preIncrementOperator = new C_Punctuation("++");
		public C_Expression expr;
	}

	public static @P(160) class C_PreDecrementExpression extends PrimaryOperator
	{
		public C_Punctuation preDecrementOperator = new C_Punctuation("--");
		public C_Expression expr;
	}
	
	public static @P(170) class C_PostIncrementExpression extends PrimaryOperator
	{
		public C_Variable var;		// Cannot be just C_Expression -- infinite loop
		public C_Punctuation postIncrementOperator = new C_Punctuation("++");
	}

	public static @P(180) class C_PostDecrementExpression extends PrimaryOperator
	{
		public C_Variable var;		// Cannot be just C_Expression -- infinite loop
		public C_Punctuation postDecrementOperator = new C_Punctuation("--");
	}

	public static @P(190) class C_SignedExpression extends PrimaryOperator
	{
		public C_PunctuationChoice signedOperator = new C_PunctuationChoice("+", "-");
		public C_Expression expr;
	}

	public static @P(200) class C_LogicalNotExpression extends PrimaryOperator
	{
		public C_Punctuation logicalNotOperator = new C_Punctuation('~');
		public C_Expression expr;
	}
		
	public static @P(210) class C_NotExpression extends PrimaryOperator
	{
		public C_Punctuation notOperator = new C_Punctuation('!');
		public C_Expression expr;
	}
	
	public static @P(220) class C_BuiltIn extends PrimaryOperator
	{
		public C_KeywordChoice logicalConstant = new C_KeywordChoice("false", "true", "NULL");
	}
	
	public static @P(230) class C_VariableExpression extends PrimaryOperator
	{
		public C_Variable variable;
	}
	
	public static @P(240) class C_AddressOfVariable extends PrimaryOperator
	{
		public C_Punctuation ampersand = new C_Punctuation('&');
		public C_Expression expr;
	}
	
	public static @P(250) class C_SizeOf extends PrimaryOperator
	{
		public C_Keyword SIZEOF = new C_Keyword("sizeof");
		public PunctuationLeftParen leftParen;
		public C_Type ctype;
		public PunctuationRightParen rightParen;
	}

	public static @P(260) class C_Parenthesized_Expression extends PrimaryOperator
	{
		public C_ParenthesizedExpression expr;
	}

	public static @P(270) class C_StarExpression extends PrimaryOperator
	{
		public PunctuationStar star;
		public C_Expression expr;
	}

	public static @P(280) class C_CommentExpression extends PrimaryOperator
	{
		public C_Comment comment;
		public C_Expression expr;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(290) class C_SubscriptExpression extends PrecedenceOperator
	{
		public C_Expression expr = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public C_Expression subscr = new C_Expression(this, AllowedPrecedence.HIGHER);
		public PunctuationRightBracket rightBracket;
	}

	public static @P(300) class C_DotSubfield extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(310) class C_ArrowSubfield extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation arrow = new C_Punctuation("->");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(320) class C_MultiplicativeExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("*", "/", "%");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(330) class C_AdditiveExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("+", "-");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(340) class C_ShiftExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("<<", ">>", ">>>");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(350) class C_RelationalExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("<", ">", "<=", ">=");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(360) class C_EqualityExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("==", "!=");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(370) class C_BitwiseAndExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseAndOperator = new C_Punctuation('&');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(380) class C_ExclusiveOrExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseXOrOperator = new C_Punctuation('^');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(390) class C_BitwiseOrExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseOrOperator = new C_Punctuation('|');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(400) class C_ConditionalAndExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation andOperator = new C_Punctuation("&&");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(410) class C_ConditionalOrExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation orOperator = new C_Punctuation("||");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(420) class C_TrueFalseExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.HIGHER);
		public C_Punctuation questionMark = new C_Punctuation('?');
		public C_Expression middle = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationColon colon;
		public C_Expression right = new C_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static @P(430) class C_AssignmentExpression extends PrecedenceOperator
	{
		public C_Expression var = new C_Expression(this, AllowedPrecedence.HIGHER);
		public C_PunctuationChoice equals = new C_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.ATLEAST);
	}
}
