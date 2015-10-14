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
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_Expression extends PrecedenceChooser
{
	public CSharp_Expression()
	{
	}
	
	public CSharp_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(CSharp_Number.class);			
		super.addTerm(CSharp_HexNumber.class);				
		super.addTerm(CSharp_Literal.class);
		super.addTerm(CSharp_Character_Literal.class);
		super.addTerm(CSharp_DotClass.class);
		super.addTerm(CSharp_CastExpression.class);
		super.addTerm(CSharp_ExpressionList.class);
		super.addTerm(CSharp_InterfaceCreationWithMethod.class);
		super.addTerm(CSharp_ClassCreationExpression.class);
		super.addTerm(CSharp_ClassCreationWithInitializers.class);
		super.addTerm(CSharp_ClassCreationWithSubscript.class);
		super.addTerm(CSharp_MethodInvocation.class);
		super.addTerm(CSharp_PreIncrementExpression.class);
		super.addTerm(CSharp_PreDecrementExpression.class);
		super.addTerm(CSharp_PostIncrementExpression.class);
		super.addTerm(CSharp_PostDecrementExpression.class);
		super.addTerm(CSharp_NegativeExpression.class);
		super.addTerm(CSharp_LogicalNotExpression.class);
		super.addTerm(CSharp_NotExpression.class);
		super.addTerm(CSharp_BuiltIn.class);
		super.addTerm(CSharp_Variable.class);
		super.addTerm(CSharp_Type.class);
		super.addTerm(CSharp_ParenthesizedExpression.class);
		super.addTerm(CSharp_CommentExpression.class);
		super.addTerm(CSharp_TypeOf.class);
		super.addTerm(CSharp_Delegation.class);
		
		// Order is critical ...
		super.addOperator(CSharp_SubscriptExpression.class);
		super.addOperator(CSharp_NamespaceExpression.class);
		super.addOperator(CSharp_SubfieldExpression.class);
		super.addOperator(CSharp_MultiplicativeExpression.class);
		super.addOperator(CSharp_AdditiveExpression.class);
		super.addOperator(CSharp_ShiftExpression.class);
		super.addOperator(CSharp_RelationalExpression.class);
		super.addOperator(CSharp_InstanceOfExpression.class);
		super.addOperator(CSharp_EqualityExpression.class);
		super.addOperator(CSharp_AndExpression.class);
		super.addOperator(CSharp_ExclusiveOrExpression.class);
		super.addOperator(CSharp_InclusiveOrExpression.class);
		super.addOperator(CSharp_ConditionalAndExpression.class);
		super.addOperator(CSharp_ConditionalOrExpression.class);
		super.addOperator(CSharp_TrueFalseExpression.class);
		super.addOperator(CSharp_AssignmentExpression.class);
		super.addOperator(CSharp_LambdaExpression.class);
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static class CSharp_BuiltIn extends TokenChooser
	{
		public CSharp_KeywordChoice builtIn = new CSharp_KeywordChoice("false", "true", "null", "this", "super");
	}
	
	public static class CSharp_ParenthesizedExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public CSharp_Expression expression;
		public PunctuationRightParen rightParen;
	}
	
	public static class CSharp_CastExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public CSharp_Type jtype;
		public PunctuationRightParen rightParen;
		public CSharp_Expression expr;
	}

	public static class CSharp_ExpressionList extends ExpressionTerm
	{
		public PunctuationLeftBrace leftBrace;
		public @OPT TokenList<CSharp_Comment> comment;
		public CSharp_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static class CSharp_InterfaceCreationWithMethod extends ExpressionTerm
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

	public static class CSharp_ClassCreationExpression extends ExpressionTerm
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_Type jtype;
		public PunctuationLeftParen leftParen;
		public @OPT TokenList<CSharp_Comment> comments;
		public @OPT CSharp_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}
	
	public static class CSharp_ClassCreationWithInitializers extends ExpressionTerm
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_Type jtype;
		public PunctuationLeftBrace leftBrace;
		public @OPT CSharp_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static class CSharp_ClassCreationWithSubscript extends ExpressionTerm
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_Type jtype;
		public TokenList<CSharp_Subscript> subscripts;
	}

	public static class CSharp_MethodInvocation extends ExpressionTerm
	{
		public CSharp_Variable methodName;
		public PunctuationLeftParen leftParen;
		public @OPT CSharp_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}
	
	public static class CSharp_DotClass extends ExpressionTerm
	{
		public CSharp_Type jtype;
		public PunctuationPeriod dot;
		public CSharp_Keyword CLASS = new CSharp_Keyword("class");
	}
	
	public static class CSharp_ArgumentList extends ExpressionTerm
	{
		public @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice("ref", "out");
		public CSharp_Expression arg;
		public @OPT TokenList<CSharp_MoreArguments> moreArgs;
		
		public static class CSharp_MoreArguments extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice("ref", "out");
			public CSharp_Expression arg;
			public @OPT TokenList<CSharp_Comment> comments;
		}
	}

	public static class CSharp_PreIncrementExpression extends ExpressionTerm
	{
		public CSharp_Punctuation preIncrementOperator = new CSharp_Punctuation("++");
		public CSharp_Variable var;
	}

	public static class CSharp_PreDecrementExpression extends ExpressionTerm
	{
		public CSharp_Punctuation preDecrementOperator = new CSharp_Punctuation("--");
		public CSharp_Variable var;
	}
	
	public static class CSharp_PostIncrementExpression extends ExpressionTerm
	{
		public CSharp_Variable var;
		public CSharp_Punctuation postIncrementOperator = new CSharp_Punctuation("++");
	}

	public static class CSharp_PostDecrementExpression extends ExpressionTerm
	{
		public CSharp_Variable var;
		public CSharp_Punctuation postDecrementOperator = new CSharp_Punctuation("--");
	}
	
	public static class CSharp_NegativeExpression extends ExpressionTerm
	{
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("-", "+");
		public CSharp_Expression expr;
	}

	public static class CSharp_LogicalNotExpression extends ExpressionTerm
	{
		public CSharp_Punctuation logicalNotOperator = new CSharp_Punctuation('~');
		public CSharp_Expression expr;
	}
	
	public static class CSharp_NotExpression extends ExpressionTerm
	{
		public CSharp_Punctuation notOperator = new CSharp_Punctuation('!');
		public CSharp_Expression expr;
	}
	
	public static class CSharp_CommentExpression extends ExpressionTerm
	{
		public CSharp_Comment comment;
		public CSharp_Expression expr;
	}
	
	public static class CSharp_TypeOf extends ExpressionTerm
	{
		public CSharp_Keyword TYPEOF = new CSharp_Keyword("typeof");
		public PunctuationLeftParen leftParen;
		public CSharp_Type type;
		public PunctuationRightParen rightParen;
	}
	
	public static class CSharp_Delegation extends ExpressionTerm
	{
		public CSharp_Keyword DELEGATE = new CSharp_Keyword("delegate");
		public CSharp_MethodParameters parameters;
		public @NEWLINE CSharp_MethodBody body;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class CSharp_LambdaExpression extends PrecedenceOperator
	{
		public CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Punctuation lambda = new CSharp_Punctuation("=>");
		public CSharp_Expression expr;
	}

	public static class CSharp_AssignmentExpression extends PrecedenceOperator
	{
		public CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public CSharp_Expression expr;
	}

	public static class CSharp_TrueFalseExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Punctuation questionMark = new CSharp_Punctuation('?');
		public CSharp_Expression middle = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationColon colon;
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static class CSharp_ConditionalOrExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation orOperator = new CSharp_Punctuation("||");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class CSharp_ConditionalAndExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation andOperator = new CSharp_Punctuation("&&");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class CSharp_InclusiveOrExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation bitwiseOrOperator = new CSharp_Punctuation('|');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_ExclusiveOrExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation bitwiseXOrOperator = new CSharp_Punctuation('^');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_AndExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation bitwiseAndOperator = new CSharp_Punctuation('&');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_EqualityExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_EqualityOperator equalityOperator;
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

		public static class CSharp_EqualityOperator extends TokenChooser
		{
			public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("==", "!=", "??");
			public CSharp_KeywordChoice asIs = new CSharp_KeywordChoice("as", "is");
		}
	}
	
	public static class CSharp_RelationalExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("<", ">", "<=", ">=");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_InstanceOfExpression extends PrecedenceOperator
	{
		public CSharp_Expression expr = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Keyword instanceOperator = new CSharp_Keyword("is");
		public CSharp_Type type;
	}

	public static class CSharp_ShiftExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("<<", ">>", ">>>");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_AdditiveExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("+", "-");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_MultiplicativeExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("*", "/", "%");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_SubfieldExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_NamespaceExpression extends PrecedenceOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_SubscriptExpression extends PrecedenceOperator
	{
		public CSharp_Expression expr = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public CSharp_Expression subscr = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public PunctuationRightBracket rightBracket;
	}
}
