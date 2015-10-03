// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypePrimitive;
import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypeUserDefined.C_TypeStar;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Character_Literal;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_HexNumber;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CPlus.CPlus_Expression.CPlus_NewExpression;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_Expression extends PrecedenceChooser
{
	public C_Expression()
	{
	}
	
	public C_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(C_Number.class);			
		super.addUnaryOperator(C_HexNumber.class);				
		super.addUnaryOperator(C_Literals.class);
		super.addUnaryOperator(C_Character_Literal.class);
		super.addUnaryOperator(C_CastExpression.class);
		super.addUnaryOperator(C_ExpressionList.class);
		super.addUnaryOperator(C_FunctionCall.class);
		super.addUnaryOperator(C_FunctionPointerCall.class);
		super.addUnaryOperator(C_PreIncrementExpression.class);
		super.addUnaryOperator(C_PreDecrementExpression.class);
		super.addUnaryOperator(C_PostIncrementExpression.class);
		super.addUnaryOperator(C_PostDecrementExpression.class);
		super.addUnaryOperator(C_SignedExpression.class);
		super.addUnaryOperator(C_LogicalNotExpression.class);
		super.addUnaryOperator(C_NotExpression.class);
		super.addUnaryOperator(C_BuiltIn.class);
		super.addUnaryOperator(C_Variable.class);
		super.addUnaryOperator(C_AddressOfVariable.class);
		super.addUnaryOperator(C_SizeOf.class);
		super.addUnaryOperator(C_ParenthesizedExpression.class);
		super.addUnaryOperator(C_StarExpression.class);
		super.addUnaryOperator(C_CommentExpression.class);
		super.addUnaryOperator(CPlus_NewExpression.class);
		
		// Order is critical ...
		super.addBinaryOperator(C_SubscriptExpression.class);
		super.addBinaryOperator(C_DotSubfield.class);
		super.addBinaryOperator(C_ArrowSubfield.class);
		super.addBinaryOperator(C_MultiplicativeExpression.class);
		super.addBinaryOperator(C_AdditiveExpression.class);
		super.addBinaryOperator(C_ShiftExpression.class);
		super.addBinaryOperator(C_RelationalExpression.class);
		super.addBinaryOperator(C_EqualityExpression.class);
		super.addBinaryOperator(C_BitwiseAndExpression.class);
		super.addBinaryOperator(C_ExclusiveOrExpression.class);
		super.addBinaryOperator(C_BitwiseOrExpression.class);
		super.addBinaryOperator(C_ConditionalAndExpression.class);
		super.addBinaryOperator(C_ConditionalOrExpression.class);
		super.addBinaryOperator(C_TrueFalseExpression.class);
		super.addBinaryOperator(C_AssignmentExpression.class);
		super.addBinaryOperator(C_CommaExpression.class);
	}
	
	///////////////////////////////////////////////
	// Primary expressions

	public static class C_Literals extends UnaryOperator
	{
		public TokenList<C_Literal> literals;
	}
	
	public static class C_PreIncrementExpression extends UnaryOperator
	{
		public C_Punctuation preIncrementOperator = new C_Punctuation("++");
		public C_Expression expr;
	}

	public static class C_PreDecrementExpression extends UnaryOperator
	{
		public C_Punctuation preDecrementOperator = new C_Punctuation("--");
		public C_Expression expr;
	}
	
	public static class C_PostIncrementExpression extends UnaryOperator
	{
		public C_Variable var;		// Cannot be just C_Expression -- infinite loop
		public C_Punctuation postIncrementOperator = new C_Punctuation("++");
	}

	public static class C_PostDecrementExpression extends UnaryOperator
	{
		public C_Variable var;		// Cannot be just C_Expression -- infinite loop
		public C_Punctuation postDecrementOperator = new C_Punctuation("--");
	}

	public static class C_SignedExpression extends UnaryOperator
	{
		public C_PunctuationChoice signedOperator = new C_PunctuationChoice("+", "-");
		public C_Expression expr;
	}

	public static class C_CastExpression extends UnaryOperator
	{
		public C_Punctuation leftParen = new C_Punctuation('(');
		public C_Type ctype;
		public C_Punctuation rightParen = new C_Punctuation(')');
		public C_Expression expr;
	}

	public static class C_LogicalNotExpression extends UnaryOperator
	{
		public C_Punctuation logicalNotOperator = new C_Punctuation('~');
		public C_Expression expr;
	}
		
	public static class C_NotExpression extends UnaryOperator
	{
		public C_Punctuation notOperator = new C_Punctuation('!');
		public C_Expression expr;
	}
	
	public static class C_AddressOfVariable extends UnaryOperator
	{
		public C_Punctuation ampersand = new C_Punctuation('&');
		public C_Expression expr;
	}
	
	public static class C_SizeOf extends UnaryOperator
	{
		public C_Keyword SIZEOF = new C_Keyword("sizeof");
		public C_Punctuation leftParen = new C_Punctuation('(');
		public C_Type ctype;
		public C_Punctuation rightParen = new C_Punctuation(')');
	}

	public static class C_ParenthesizedExpression extends UnaryOperator
	{
		public C_Punctuation leftParen = new C_Punctuation('(');
		public C_Expression expression;
		public C_Punctuation rightParen = new C_Punctuation(')');
	}

	public static class C_BuiltIn extends UnaryOperator
	{
		public C_KeywordChoice logicalConstant = new C_KeywordChoice("false", "true", "NULL");
	}
			
	public static class C_ExpressionList extends UnaryOperator
	{
		public C_Punctuation leftBrace = new C_Punctuation('{');
		public C_ArgumentList valueList;
		public @OPT C_Comment comment;
		public C_Punctuation rightBrace = new C_Punctuation('}');
	}
	
	public static class C_CommentExpression extends UnaryOperator
	{
		public C_Comment comment;
		public C_Expression expr;
	}

	public static class C_StarExpression extends UnaryOperator
	{
		public C_Punctuation star = new C_Punctuation('*');
		public C_Expression expr;
	}

	public static class C_FunctionCall extends UnaryOperator
	{
		public C_Variable functionName;
		public C_Punctuation leftParen = new C_Punctuation('(');
		public @OPT C_ArgumentList argList;
		public C_Punctuation rightParen = new C_Punctuation(')');
	}
				
	public static class C_FunctionPointerCall extends UnaryOperator
	{
		public C_Punctuation leftParen1 = new C_Punctuation('(');
		public @OPT C_Punctuation star2 = new C_Punctuation('*');
		public C_Variable mathodName;
		public C_Punctuation rightParen1 = new C_Punctuation(')');
		public C_Punctuation leftParen2 = new C_Punctuation('(');
		public @OPT C_ArgumentList argList;
		public C_Punctuation rightParen2 = new C_Punctuation(')');
	}

	public static class C_ArgumentList extends UnaryOperator
	{
		public C_ExpressionArg arg;
		public @OPT C_Comment comment;
		public @OPT TokenList<C_MoreArguments> moreArgs;
		public @OPT @CURIOUS("Extra comma") C_Punctuation comma = new C_Punctuation(',');
		
		public static class C_ExpressionArg extends TokenChooser
		{
			public @FIRST C_Expression expr;
			public C_TypePrimitive primitiveType;
			
			public static class C_ExpressionArgType extends TokenSequence
			{
				public C_Identifier_Reference typeRef;
				public TokenList<C_TypeStar> stars;
			}
		}
		
		public static class C_MoreArguments extends TokenSequence
		{
			public C_Punctuation comma = new C_Punctuation(',');
			public @OPT C_Comment comment1;
			public C_ExpressionArg arg;
			public @OPT C_Comment comment2;
		}
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class C_CommaExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation comma = new C_Punctuation(',');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_AssignmentExpression extends BinaryOperator
	{
		public C_Expression var = new C_Expression(this, AllowedPrecedence.HIGHER);
		public C_AssignmentOperator assignmentOperator;
		public C_Expression expr;
			
		public static class C_AssignmentOperator extends TokenChooser
		{
			public C_PunctuationChoice equals = new C_PunctuationChoice(
					"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		}
	}

	public static class C_TrueFalseExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.HIGHER);
		public C_Punctuation questionMark = new C_Punctuation('?');
		public C_Expression middle = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation colon = new C_Punctuation(':');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static class C_ConditionalOrExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation orOperator = new C_Punctuation("||");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class C_ConditionalAndExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation andOperator = new C_Punctuation("&&");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
		
	public static class C_BitwiseOrExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseOrOperator = new C_Punctuation('|');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_ExclusiveOrExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseXOrOperator = new C_Punctuation('^');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_BitwiseAndExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseAndOperator = new C_Punctuation('&');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_EqualityExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("==", "!=");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class C_RelationalExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("<", ">", "<=", ">=");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_InstanceOfExpression extends BinaryOperator
	{
		public C_Expression expr = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Keyword instanceOperator = new C_Keyword("instanceof");
		public C_Type type;
	}

	public static class C_ShiftExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("<<", ">>", ">>>");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_AdditiveExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("+", "-");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_MultiplicativeExpression extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("*", "/", "%");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_DotSubfield extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation dot = new C_Punctuation('.');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_ArrowSubfield extends BinaryOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation arrow = new C_Punctuation("->");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_SubscriptExpression extends BinaryOperator
	{
		public C_Expression expr = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation leftBracket = new C_Punctuation('[');
		public C_Expression subscr = new C_Expression(this, AllowedPrecedence.HIGHER);
		public C_Punctuation rightBracket = new C_Punctuation(']');
	}
}
