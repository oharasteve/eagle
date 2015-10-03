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
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Expression extends PrecedenceChooser
{
	public CSharp_Expression()
	{
	}
	
	public CSharp_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(CSharp_Number.class);			
		super.addUnaryOperator(CSharp_HexNumber.class);				
		super.addUnaryOperator(CSharp_Literal.class);
		super.addUnaryOperator(CSharp_Character_Literal.class);
		super.addUnaryOperator(CSharp_DotClass.class);
		super.addUnaryOperator(CSharp_CastExpression.class);
		super.addUnaryOperator(CSharp_ExpressionList.class);
		super.addUnaryOperator(CSharp_InterfaceCreationWithMethod.class);
		super.addUnaryOperator(CSharp_ClassCreationExpression.class);
		super.addUnaryOperator(CSharp_ClassCreationWithInitializers.class);
		super.addUnaryOperator(CSharp_ClassCreationWithSubscript.class);
		super.addUnaryOperator(CSharp_MethodInvocation.class);
		super.addUnaryOperator(CSharp_PreIncrementExpression.class);
		super.addUnaryOperator(CSharp_PreDecrementExpression.class);
		super.addUnaryOperator(CSharp_PostIncrementExpression.class);
		super.addUnaryOperator(CSharp_PostDecrementExpression.class);
		super.addUnaryOperator(CSharp_NegativeExpression.class);
		super.addUnaryOperator(CSharp_LogicalNotExpression.class);
		super.addUnaryOperator(CSharp_NotExpression.class);
		super.addUnaryOperator(CSharp_BuiltIn.class);
		super.addUnaryOperator(CSharp_Variable.class);
		super.addUnaryOperator(CSharp_Type.class);
		super.addUnaryOperator(CSharp_ParenthesizedExpression.class);
		super.addUnaryOperator(CSharp_CommentExpression.class);
		super.addUnaryOperator(CSharp_TypeOf.class);
		super.addUnaryOperator(CSharp_Delegation.class);
		
		// Order is critical ...
		super.addBinaryOperator(CSharp_SubscriptExpression.class);
		super.addBinaryOperator(CSharp_NamespaceExpression.class);
		super.addBinaryOperator(CSharp_SubfieldExpression.class);
		super.addBinaryOperator(CSharp_MultiplicativeExpression.class);
		super.addBinaryOperator(CSharp_AdditiveExpression.class);
		super.addBinaryOperator(CSharp_ShiftExpression.class);
		super.addBinaryOperator(CSharp_RelationalExpression.class);
		super.addBinaryOperator(CSharp_InstanceOfExpression.class);
		super.addBinaryOperator(CSharp_EqualityExpression.class);
		super.addBinaryOperator(CSharp_AndExpression.class);
		super.addBinaryOperator(CSharp_ExclusiveOrExpression.class);
		super.addBinaryOperator(CSharp_InclusiveOrExpression.class);
		super.addBinaryOperator(CSharp_ConditionalAndExpression.class);
		super.addBinaryOperator(CSharp_ConditionalOrExpression.class);
		super.addBinaryOperator(CSharp_TrueFalseExpression.class);
		super.addBinaryOperator(CSharp_AssignmentExpression.class);
		super.addBinaryOperator(CSharp_LambdaExpression.class);
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static class CSharp_BuiltIn extends TokenChooser
	{
		public CSharp_KeywordChoice builtIn = new CSharp_KeywordChoice("false", "true", "null", "this", "super");
	}
	
	public static class CSharp_ParenthesizedExpression extends UnaryOperator
	{
		public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
		public CSharp_Expression expression;
		public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	}
	
	public static class CSharp_CastExpression extends UnaryOperator
	{
		public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
		public CSharp_Type jtype;
		public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
		public CSharp_Expression expr;
	}

	public static class CSharp_ExpressionList extends UnaryOperator
	{
		public CSharp_Punctuation leftBrace = new CSharp_Punctuation('{');
		public @OPT TokenList<CSharp_Comment> comment;
		public CSharp_ArgumentList valueList;
		public CSharp_Punctuation rightBrace = new CSharp_Punctuation('}');
	}
	
	public static class CSharp_InterfaceCreationWithMethod extends UnaryOperator
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_KeywordChoice jinterface = new CSharp_KeywordChoice(
				"Runnable", "ActionListener", "WindowAdapter");
		public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
		public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
		public CSharp_Punctuation leftBrace = new CSharp_Punctuation('{');
		public CSharp_Method method;
		public CSharp_Punctuation rightBrace = new CSharp_Punctuation('}');
	}

	public static class CSharp_ClassCreationExpression extends UnaryOperator
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_Type jtype;
		public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
		public @OPT TokenList<CSharp_Comment> comments;
		public @OPT CSharp_ArgumentList argList;
		public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	}
	
	public static class CSharp_ClassCreationWithInitializers extends UnaryOperator
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_Type jtype;
		public CSharp_Punctuation leftBrace = new CSharp_Punctuation('{');
		public @OPT CSharp_ArgumentList valueList;
		public CSharp_Punctuation rightBrace = new CSharp_Punctuation('}');
	}
	
	public static class CSharp_ClassCreationWithSubscript extends UnaryOperator
	{
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
		public CSharp_Type jtype;
		public TokenList<CSharp_Subscript> subscripts;
	}

	public static class CSharp_MethodInvocation extends UnaryOperator
	{
		public CSharp_Variable methodName;
		public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
		public @OPT CSharp_ArgumentList argList;
		public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	}
	
	public static class CSharp_DotClass extends UnaryOperator
	{
		public CSharp_Type jtype;
		public CSharp_Punctuation dot = new CSharp_Punctuation('.');
		public CSharp_Keyword CLASS = new CSharp_Keyword("class");
	}
	
	public static class CSharp_ArgumentList extends UnaryOperator
	{
		public @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice("ref", "out");
		public CSharp_Expression arg;
		public @OPT TokenList<CSharp_MoreArguments> moreArgs;
		
		public static class CSharp_MoreArguments extends TokenSequence
		{
			public CSharp_Punctuation comma = new CSharp_Punctuation(',');
			public @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice("ref", "out");
			public CSharp_Expression arg;
			public @OPT TokenList<CSharp_Comment> comments;
		}
	}

	public static class CSharp_PreIncrementExpression extends UnaryOperator
	{
		public CSharp_Punctuation preIncrementOperator = new CSharp_Punctuation("++");
		public CSharp_Variable var;
	}

	public static class CSharp_PreDecrementExpression extends UnaryOperator
	{
		public CSharp_Punctuation preDecrementOperator = new CSharp_Punctuation("--");
		public CSharp_Variable var;
	}
	
	public static class CSharp_PostIncrementExpression extends UnaryOperator
	{
		public CSharp_Variable var;
		public CSharp_Punctuation postIncrementOperator = new CSharp_Punctuation("++");
	}

	public static class CSharp_PostDecrementExpression extends UnaryOperator
	{
		public CSharp_Variable var;
		public CSharp_Punctuation postDecrementOperator = new CSharp_Punctuation("--");
	}
	
	public static class CSharp_NegativeExpression extends UnaryOperator
	{
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("-", "+");
		public CSharp_Expression expr;
	}

	public static class CSharp_LogicalNotExpression extends UnaryOperator
	{
		public CSharp_Punctuation logicalNotOperator = new CSharp_Punctuation('~');
		public CSharp_Expression expr;
	}
	
	public static class CSharp_NotExpression extends UnaryOperator
	{
		public CSharp_Punctuation notOperator = new CSharp_Punctuation('!');
		public CSharp_Expression expr;
	}
	
	public static class CSharp_CommentExpression extends UnaryOperator
	{
		public CSharp_Comment comment;
		public CSharp_Expression expr;
	}
	
	public static class CSharp_TypeOf extends UnaryOperator
	{
		public CSharp_Keyword TYPEOF = new CSharp_Keyword("typeof");
		public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
		public CSharp_Type type;
		public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	}
	
	public static class CSharp_Delegation extends UnaryOperator
	{
		public CSharp_Keyword DELEGATE = new CSharp_Keyword("delegate");
		public CSharp_MethodParameters parameters;
		public @NEWLINE CSharp_MethodBody body;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class CSharp_LambdaExpression extends BinaryOperator
	{
		public CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Punctuation lambda = new CSharp_Punctuation("=>");
		public CSharp_Expression expr;
	}

	public static class CSharp_AssignmentExpression extends BinaryOperator
	{
		public CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public CSharp_Expression expr;
	}

	public static class CSharp_TrueFalseExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Punctuation questionMark = new CSharp_Punctuation('?');
		public CSharp_Expression middle = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation colon = new CSharp_Punctuation(':');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static class CSharp_ConditionalOrExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation orOperator = new CSharp_Punctuation("||");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class CSharp_ConditionalAndExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation andOperator = new CSharp_Punctuation("&&");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class CSharp_InclusiveOrExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation bitwiseOrOperator = new CSharp_Punctuation('|');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_ExclusiveOrExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation bitwiseXOrOperator = new CSharp_Punctuation('^');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_AndExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation bitwiseAndOperator = new CSharp_Punctuation('&');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_EqualityExpression extends BinaryOperator
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
	
	public static class CSharp_RelationalExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("<", ">", "<=", ">=");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_InstanceOfExpression extends BinaryOperator
	{
		public CSharp_Expression expr = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Keyword instanceOperator = new CSharp_Keyword("is");
		public CSharp_Type type;
	}

	public static class CSharp_ShiftExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("<<", ">>", ">>>");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_AdditiveExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("+", "-");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_MultiplicativeExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("*", "/", "%");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_SubfieldExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation dot = new CSharp_Punctuation('.');
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_NamespaceExpression extends BinaryOperator
	{
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CSharp_SubscriptExpression extends BinaryOperator
	{
		public CSharp_Expression expr = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Punctuation leftBracket = new CSharp_Punctuation('[');
		public CSharp_Expression subscr = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Punctuation rightBracket = new CSharp_Punctuation(']');
	}
}
