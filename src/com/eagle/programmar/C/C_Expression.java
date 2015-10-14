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
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationHyphen;
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
	public C_Expression()
	{
	}
	
	public C_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(C_Number.class);			
		super.addTerm(C_HexNumber.class);				
		super.addTerm(C_Literals.class);
		super.addTerm(C_Character_Literal.class);
		super.addTerm(C_CastExpression.class);
		super.addTerm(C_ExpressionList.class);
		super.addTerm(C_FunctionCall.class);
		super.addTerm(C_FunctionPointerCall.class);
		super.addTerm(C_PreIncrementExpression.class);
		super.addTerm(C_PreDecrementExpression.class);
		super.addTerm(C_PostIncrementExpression.class);
		super.addTerm(C_PostDecrementExpression.class);
		super.addTerm(C_SignedExpression.class);
		super.addTerm(C_LogicalNotExpression.class);
		super.addTerm(C_NotExpression.class);
		super.addTerm(C_BuiltIn.class);
		super.addTerm(C_Variable.class);
		super.addTerm(C_AddressOfVariable.class);
		super.addTerm(C_SizeOf.class);
		super.addTerm(C_ParenthesizedExpression.class);
		super.addTerm(C_StarExpression.class);
		super.addTerm(C_CommentExpression.class);
		super.addTerm(CPlus_NewExpression.class);
		
		// Order is critical ...
		super.addOperator(C_SubscriptExpression.class);
		super.addOperator(C_DotSubfield.class);
		super.addOperator(C_ArrowSubfield.class);
		super.addOperator(C_MultiplicativeExpression.class);
		super.addOperator(C_AdditiveExpression.class);
		super.addOperator(C_ShiftExpression.class);
		super.addOperator(C_RelationalExpression.class);
		super.addOperator(C_EqualityExpression.class);
		super.addOperator(C_BitwiseAndExpression.class);
		super.addOperator(C_ExclusiveOrExpression.class);
		super.addOperator(C_BitwiseOrExpression.class);
		super.addOperator(C_ConditionalAndExpression.class);
		super.addOperator(C_ConditionalOrExpression.class);
		super.addOperator(C_TrueFalseExpression.class);
		super.addOperator(C_AssignmentExpression.class);
		super.addOperator(C_CommaExpression.class);
	}
	
	///////////////////////////////////////////////
	// Primary expressions

	public static class C_Literals extends ExpressionTerm
	{
		public TokenList<C_Literal> literals;
	}
	
	public static class C_PreIncrementExpression extends ExpressionTerm
	{
		public C_Punctuation preIncrementOperator = new C_Punctuation("++");
		public C_Expression expr;
	}

	public static class C_PreDecrementExpression extends ExpressionTerm
	{
		public C_Punctuation preDecrementOperator = new C_Punctuation("--");
		public C_Expression expr;
	}
	
	public static class C_PostIncrementExpression extends ExpressionTerm
	{
		public C_Variable var;		// Cannot be just C_Expression -- infinite loop
		public C_Punctuation postIncrementOperator = new C_Punctuation("++");
	}

	public static class C_PostDecrementExpression extends ExpressionTerm
	{
		public C_Variable var;		// Cannot be just C_Expression -- infinite loop
		public C_Punctuation postDecrementOperator = new C_Punctuation("--");
	}

	public static class C_SignedExpression extends ExpressionTerm
	{
		public C_PunctuationChoice signedOperator = new C_PunctuationChoice("+", "-");
		public C_Expression expr;
	}

	public static class C_CastExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public C_Type ctype;
		public PunctuationRightParen rightParen;
		public C_Expression expr;
	}

	public static class C_LogicalNotExpression extends ExpressionTerm
	{
		public C_Punctuation logicalNotOperator = new C_Punctuation('~');
		public C_Expression expr;
	}
		
	public static class C_NotExpression extends ExpressionTerm
	{
		public C_Punctuation notOperator = new C_Punctuation('!');
		public C_Expression expr;
	}
	
	public static class C_AddressOfVariable extends ExpressionTerm
	{
		public C_Punctuation ampersand = new C_Punctuation('&');
		public C_Expression expr;
	}
	
	public static class C_SizeOf extends ExpressionTerm
	{
		public C_Keyword SIZEOF = new C_Keyword("sizeof");
		public PunctuationLeftParen leftParen;
		public C_Type ctype;
		public PunctuationRightParen rightParen;
	}

	public static class C_ParenthesizedExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public C_Expression expression;
		public PunctuationRightParen rightParen;
	}

	public static class C_BuiltIn extends ExpressionTerm
	{
		public C_KeywordChoice logicalConstant = new C_KeywordChoice("false", "true", "NULL");
	}
			
	public static class C_ExpressionList extends ExpressionTerm
	{
		public PunctuationLeftBrace leftBrace;
		public C_ArgumentList valueList;
		public @OPT C_Comment comment;
		public PunctuationRightBrace rightBrace;
	}
	
	public static class C_CommentExpression extends ExpressionTerm
	{
		public C_Comment comment;
		public C_Expression expr;
	}

	public static class C_StarExpression extends ExpressionTerm
	{
		public PunctuationStar star;
		public C_Expression expr;
	}

	public static class C_FunctionCall extends ExpressionTerm
	{
		public C_Variable functionName;
		public PunctuationLeftParen leftParen;
		public @OPT C_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}
				
	public static class C_FunctionPointerCall extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen1;
		public @OPT PunctuationHyphen star2;
		public C_Variable mathodName;
		public PunctuationRightParen rightParen1;
		public PunctuationLeftParen leftParen2;
		public @OPT C_ArgumentList argList;
		public PunctuationRightParen rightParen2;
	}

	public static class C_ArgumentList extends ExpressionTerm
	{
		public C_ExpressionArg arg;
		public @OPT C_Comment comment;
		public @OPT TokenList<C_MoreArguments> moreArgs;
		public @OPT @CURIOUS("Extra comma") PunctuationComma comma;
		
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
			public PunctuationComma comma;
			public @OPT C_Comment comment1;
			public C_ExpressionArg arg;
			public @OPT C_Comment comment2;
		}
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class C_CommaExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationComma comma;
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_AssignmentExpression extends PrecedenceOperator
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

	public static class C_TrueFalseExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.HIGHER);
		public C_Punctuation questionMark = new C_Punctuation('?');
		public C_Expression middle = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationColon colon;
		public C_Expression right = new C_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static class C_ConditionalOrExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation orOperator = new C_Punctuation("||");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class C_ConditionalAndExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation andOperator = new C_Punctuation("&&");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
		
	public static class C_BitwiseOrExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseOrOperator = new C_Punctuation('|');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_ExclusiveOrExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseXOrOperator = new C_Punctuation('^');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_BitwiseAndExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseAndOperator = new C_Punctuation('&');
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_EqualityExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("==", "!=");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class C_RelationalExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("<", ">", "<=", ">=");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_InstanceOfExpression extends PrecedenceOperator
	{
		public C_Expression expr = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Keyword instanceOperator = new C_Keyword("instanceof");
		public C_Type type;
	}

	public static class C_ShiftExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("<<", ">>", ">>>");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_AdditiveExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("+", "-");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_MultiplicativeExpression extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("*", "/", "%");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_DotSubfield extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_ArrowSubfield extends PrecedenceOperator
	{
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation arrow = new C_Punctuation("->");
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class C_SubscriptExpression extends PrecedenceOperator
	{
		public C_Expression expr = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public C_Expression subscr = new C_Expression(this, AllowedPrecedence.HIGHER);
		public PunctuationRightBracket rightBracket;
	}
}
