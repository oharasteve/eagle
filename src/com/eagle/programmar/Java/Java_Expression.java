// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_Class.Java_ClassElement;
import com.eagle.programmar.Java.Java_Type.Java_GenericType;
import com.eagle.programmar.Java.Terminals.Java_Character_Literal;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_HexNumber;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tests.EagleTestable;
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

public class Java_Expression extends PrecedenceChooser
{
	public Java_Expression()
	{
	}
	
	public Java_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(Java_HexNumber.class);
		super.addTerm(Java_Number.class);
		super.addTerm(Java_Literal.class);
		super.addTerm(Java_Character_Literal.class);
		super.addTerm(Java_DotClass.class);
		super.addTerm(Java_CastExpression.class);
		super.addTerm(Java_ExpressionList.class);
		super.addTerm(Java_InterfaceCreationWithMethod.class);
		super.addTerm(Java_ClassCreationExpression.class);
		super.addTerm(Java_ClassCreationWithInitializers.class);
		super.addTerm(Java_ClassCreationWithSubscript.class);
		super.addTerm(Java_MethodInvocation.class);
		super.addTerm(Java_PreIncrementExpression.class);
		super.addTerm(Java_PreDecrementExpression.class);
		super.addTerm(Java_PostIncrementExpression.class);
		super.addTerm(Java_PostDecrementExpression.class);
		super.addTerm(Java_NegativeExpression.class);
		super.addTerm(Java_LogicalNotExpression.class);
		super.addTerm(Java_NotExpression.class);
		super.addTerm(Java_BuiltIn.class);
		super.addTerm(Java_Variable.class);
		super.addTerm(Java_ParenthesizedExpression.class);
		super.addTerm(Java_CommentExpression.class);
		super.addOperator(Java_SubscriptExpression.class);
		
		// Order is critical ...
		super.addOperator(Java_Subfield.class);
		super.addOperator(Java_MultiplicativeExpression.class);
		super.addOperator(Java_AdditiveExpression.class);
		super.addOperator(Java_ShiftExpression.class);
		super.addOperator(Java_RelationalExpression.class);
		super.addOperator(Java_InstanceOfExpression.class);
		super.addOperator(Java_EqualityExpression.class);
		super.addOperator(Java_AndExpression.class);
		super.addOperator(Java_ExclusiveOrExpression.class);
		super.addOperator(Java_InclusiveOrExpression.class);
		super.addOperator(Java_ConditionalAndExpression.class);
		super.addOperator(Java_ConditionalOrExpression.class);
		super.addOperator(Java_AssignmentExpression.class);
		super.addOperator(Java_TrueFalseExpression.class);
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static class Java_BuiltIn extends TokenChooser implements EagleTestable, EagleRunnable
	{
		public Java_KeywordChoice builtinConstant = new Java_KeywordChoice("false", "true", "null", "this", "super");
		
		@Override
		public void addTests()
		{
			Java_Tests.addTestExpr("TrueFalse1", "false || false", "false");
			Java_Tests.addTestExpr("TrueFalse2", "false || true", "true");
			Java_Tests.addTestExpr("TrueFalse3", "true && false", "false");
			Java_Tests.addTestExpr("TrueFalse4", "false && true", "false");
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			Java_KeywordChoice kw = (Java_KeywordChoice) _whichToken;
			String name = kw.toString();
			if (name.equals("false"))
			{
				interpreter.pushBool(false);
			}
			else if (name.equals("true"))
			{
				interpreter.pushBool(true);
			}
			else throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + name);
		}
	}
	
	public static class Java_ParenthesizedExpression extends ExpressionTerm implements EagleRunnable
	{
		public PunctuationLeftParen leftParen;
		public Java_Expression expression;
		public PunctuationRightParen rightParen;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(expression);
		}
	}
	
	public static class Java_CastExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public Java_Type jtype;
		public PunctuationRightParen rightParen;
		public Java_Expression expr;
	}

	public static class Java_ExpressionList extends ExpressionTerm
	{
		public PunctuationLeftBrace leftBrace;
		public @OPT TokenList<Java_Comment> comment;
		public @OPT Java_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static class Java_InterfaceCreationWithMethod extends ExpressionTerm
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_KeywordChoice jinterface = new Java_KeywordChoice( 
				"Runnable", "ActionListener", "WindowAdapter");
		public PunctuationLeftParen leftParen;
		public PunctuationRightParen rightParen;
		public PunctuationLeftBrace leftBrace;
		public Java_Method method;
		public PunctuationRightBrace rightBrace;
	}

	public static class Java_ClassCreationExpression extends ExpressionTerm
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_Type jtype;
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE @OPT TokenList<Java_Comment> comments;
		public @NOSPACE @OPT Java_ArgumentList argList;
		public @NOSPACE PunctuationRightParen rightParen;
		public @OPT Java_ClassOverride override;

		public static class Java_ClassOverride extends TokenSequence
		{
			public PunctuationLeftBrace leftBrace;
			public TokenList<Java_ClassElement> elementList;
			public PunctuationRightBrace rightBrace;
		}
	}
	
	public static class Java_ClassCreationWithInitializers extends ExpressionTerm
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_Type jtype;
		public PunctuationLeftBrace leftBrace;
		public @OPT Java_ArgumentList valueList;
		public PunctuationRightBrace rightBrace;
	}
	
	public static class Java_ClassCreationWithSubscript extends ExpressionTerm
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_Type jtype;
		public TokenList<Java_Subscript> subscripts;
	}
	
	public static class Java_MethodInvocation extends ExpressionTerm implements EagleRunnable
	{
		public Java_Variable methodName;
		public @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE @OPT Java_ArgumentList argList;
		public @NOSPACE PunctuationRightParen rightParen;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			// Assume System.out.println(exp);
			EagleValue result = interpreter.getEagleValue(argList.arg);
			System.out.println(result.toString());
		}
	}
	
	public static class Java_DotClass extends ExpressionTerm
	{
		public Java_Type jtype;
		public @NOSPACE PunctuationPeriod dot;
		public @NOSPACE Java_Keyword CLASS = new Java_Keyword("class");
	}
	
	public static class Java_ArgumentList extends ExpressionTerm
	{
		public Java_Expression arg;
		public @OPT TokenList<Java_Comment> comment;
		public @OPT TokenList<Java_MoreArguments> moreArgs;
		public @OPT @CURIOUS("Extra comma") PunctuationComma comma;
		
		public static class Java_MoreArguments extends TokenSequence
		{
			public @NOSPACE PunctuationComma comma;
			public @OPT TokenList<Java_Comment> comment1;
			public Java_Expression arg;
			public @OPT TokenList<Java_Comment> comment2;
		}
	}

	public static class Java_PreIncrementExpression extends ExpressionTerm
	{
		public Java_Punctuation preIncrementOperator = new Java_Punctuation("++");
		public Java_Variable var;
	}

	public static class Java_PreDecrementExpression extends ExpressionTerm
	{
		public Java_Punctuation preDecrementOperator = new Java_Punctuation("--");
		public Java_Variable var;
	}
	
	public static class Java_PostIncrementExpression extends ExpressionTerm
	{
		public Java_Variable var;
		public Java_Punctuation postIncrementOperator = new Java_Punctuation("++");
	}

	public static class Java_PostDecrementExpression extends ExpressionTerm
	{
		public Java_Variable var;
		public Java_Punctuation postDecrementOperator = new Java_Punctuation("--");
	}
	
	public static class Java_NegativeExpression extends ExpressionTerm
	{
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("-", "+");
		public Java_Expression expr;
	}

	public static class Java_LogicalNotExpression extends ExpressionTerm
	{
		public Java_Punctuation logicalNotOperator = new Java_Punctuation('~');
		public Java_Expression expr;
	}
	
	public static class Java_NotExpression extends ExpressionTerm
	{
		public Java_Punctuation notOperator = new Java_Punctuation('!');
		public Java_Expression expr;
	}
	
	public static class Java_CommentExpression extends ExpressionTerm
	{
		public Java_Comment comment;
		public Java_Expression expr;
	}


	///////////////////////////////////////////////
	// Binary expressions

	public static class Java_SubscriptExpression extends PrecedenceOperator
	{
		public Java_Expression expr = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public PunctuationLeftBracket leftBracket;
		public @OPT Java_Expression subscr;
		public PunctuationRightBracket rightBracket;
	}

	public static class Java_AssignmentExpression extends PrecedenceOperator
	{
		public Java_Expression var = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_AssignmentOperator assignmentOperator;
		public Java_Expression expr;

		public static class Java_AssignmentOperator extends TokenChooser
		{
			public Java_PunctuationChoice equals = new Java_PunctuationChoice(
					"=",
					"*=",
					"/=",
					"%=",
					"+=",
					"-=",
					"<<=",
					">>=",
					">>>=",
					"&=",
					"^=",
					"|=");
		}
	}

	public static class Java_TrueFalseExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_Punctuation questionMark = new Java_Punctuation('?');
		public Java_Expression middle = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationColon colon;
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static class Java_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation orOperator = new Java_Punctuation("||");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean leftValue = interpreter.getBoolValue(left);
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue || rightValue);
		}
	}
	
	public static class Java_ConditionalAndExpression extends PrecedenceOperator implements EagleRunnable
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation andOperator = new Java_Punctuation("&&");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean leftValue = interpreter.getBoolValue(left);
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue && rightValue);
		}
	}
	
	public static class Java_InclusiveOrExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation bitwiseOrOperator = new Java_Punctuation('|');
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Java_ExclusiveOrExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation bitwiseXOrOperator = new Java_Punctuation('^');
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Java_AndExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation bitwiseAndOperator = new Java_Punctuation('&');
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Java_EqualityExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("==", "!=");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Java_RelationalExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("<", ">", "<=", ">=");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Java_InstanceOfExpression extends PrecedenceOperator
	{
		public Java_Expression expr = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Keyword instanceOperator = new Java_Keyword("instanceof");
		public Java_Type type;
	}

	public static class Java_ShiftExpression extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_ShiftOperator shiftOperator;
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		public static class Java_ShiftOperator extends TokenChooser
		{
			public @FIRST Java_PunctuationChoice operator = new Java_PunctuationChoice(">>>", "<<", ">>");
		}
	}

	public static class Java_AdditiveExpression extends PrecedenceOperator implements EagleTestable, EagleRunnable
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("+", "-");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		@Override
		public void addTests()
		{
			Java_Tests.addTestExpr("Add1", "2 + 3 + 4", "9");
			Java_Tests.addTestExpr("Add2", "2 + 3 * 4", "14");
			Java_Tests.addTestExpr("Add3", "2 * 3 + 4", "10");
			Java_Tests.addTestExpr("Subtract1", "27-10-1", "16");
			Java_Tests.addTestExpr("Subtract2", "27-(10-1)", "18");
			Java_Tests.addTestExpr("FiveSix", "five + six", "11");
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			if (operator.toString().equals("+"))
			{
				interpreter.pushInt(leftValue + rightValue);
			}
			else
			{
				interpreter.pushInt(leftValue - rightValue);
			}
		}
	}

	public static class Java_MultiplicativeExpression extends PrecedenceOperator implements EagleTestable, EagleRunnable
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("*", "/", "%");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		@Override
		public void addTests()
		{
			Java_Tests.addTestExpr("Mult1", "2 * 3 * 4", "24");
			Java_Tests.addTestExpr("Divide1", "24 / 6", "4");
			Java_Tests.addTestExpr("Mod1", "43 % 10", "3");
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			if (operator.toString().equals("*"))
			{
				interpreter.pushInt(leftValue * rightValue);
			}
			else if (operator.toString().equals("/"))
			{
				interpreter.pushInt(leftValue / rightValue);
			}
			else
			{
				interpreter.pushInt(leftValue % rightValue);
			}
		}
	}

	public static class Java_Subfield extends PrecedenceOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE PunctuationPeriod dot;
		public @OPT @NOSPACE Java_GenericType genericType;
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}
}
