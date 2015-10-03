// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

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
import com.eagle.tests.EagleInterpreter.EagleValue;
import com.eagle.tests.EagleRunnable;
import com.eagle.tests.EagleTestable;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Java_Expression extends PrecedenceChooser
{
	public Java_Expression()
	{
	}
	
	public Java_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(Java_HexNumber.class);
		super.addUnaryOperator(Java_Number.class);
		super.addUnaryOperator(Java_Literal.class);
		super.addUnaryOperator(Java_Character_Literal.class);
		super.addUnaryOperator(Java_DotClass.class);
		super.addUnaryOperator(Java_CastExpression.class);
		super.addUnaryOperator(Java_ExpressionList.class);
		super.addUnaryOperator(Java_InterfaceCreationWithMethod.class);
		super.addUnaryOperator(Java_ClassCreationExpression.class);
		super.addUnaryOperator(Java_ClassCreationWithInitializers.class);
		super.addUnaryOperator(Java_ClassCreationWithSubscript.class);
		super.addUnaryOperator(Java_MethodInvocation.class);
		super.addUnaryOperator(Java_PreIncrementExpression.class);
		super.addUnaryOperator(Java_PreDecrementExpression.class);
		super.addUnaryOperator(Java_PostIncrementExpression.class);
		super.addUnaryOperator(Java_PostDecrementExpression.class);
		super.addUnaryOperator(Java_NegativeExpression.class);
		super.addUnaryOperator(Java_LogicalNotExpression.class);
		super.addUnaryOperator(Java_NotExpression.class);
		super.addUnaryOperator(Java_BuiltIn.class);
		super.addUnaryOperator(Java_Variable.class);
		super.addUnaryOperator(Java_ParenthesizedExpression.class);
		super.addUnaryOperator(Java_CommentExpression.class);
		super.addBinaryOperator(Java_SubscriptExpression.class);
		
		// Order is critical ...
		super.addBinaryOperator(Java_Subfield.class);
		super.addBinaryOperator(Java_MultiplicativeExpression.class);
		super.addBinaryOperator(Java_AdditiveExpression.class);
		super.addBinaryOperator(Java_ShiftExpression.class);
		super.addBinaryOperator(Java_RelationalExpression.class);
		super.addBinaryOperator(Java_InstanceOfExpression.class);
		super.addBinaryOperator(Java_EqualityExpression.class);
		super.addBinaryOperator(Java_AndExpression.class);
		super.addBinaryOperator(Java_ExclusiveOrExpression.class);
		super.addBinaryOperator(Java_InclusiveOrExpression.class);
		super.addBinaryOperator(Java_ConditionalAndExpression.class);
		super.addBinaryOperator(Java_ConditionalOrExpression.class);
		super.addBinaryOperator(Java_AssignmentExpression.class);
		super.addBinaryOperator(Java_TrueFalseExpression.class);
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
	
	public static class Java_ParenthesizedExpression extends UnaryOperator implements EagleRunnable
	{
		public Java_Punctuation leftParen = new Java_Punctuation('(');
		public Java_Expression expression;
		public Java_Punctuation rightParen = new Java_Punctuation(')');
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			expression.tryToInterpret(interpreter);
		}
	}
	
	public static class Java_CastExpression extends UnaryOperator
	{
		public Java_Punctuation leftParen = new Java_Punctuation('(');
		public Java_Type jtype;
		public Java_Punctuation rightParen = new Java_Punctuation(')');
		public Java_Expression expr;
	}

	public static class Java_ExpressionList extends UnaryOperator
	{
		int a;
		public Java_Punctuation leftBrace = new Java_Punctuation('{');
		public @OPT TokenList<Java_Comment> comment;
		public @OPT Java_ArgumentList valueList;
		public Java_Punctuation rightBrace = new Java_Punctuation('}');
	}
	
	public static class Java_InterfaceCreationWithMethod extends UnaryOperator
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_KeywordChoice jinterface = new Java_KeywordChoice( 
				"Runnable", "ActionListener", "WindowAdapter");
		public Java_Punctuation leftParen = new Java_Punctuation('(');
		public Java_Punctuation rightParen = new Java_Punctuation(')');
		public Java_Punctuation leftBrace = new Java_Punctuation('{');
		public Java_Method method;
		public Java_Punctuation rightBrace = new Java_Punctuation('}');
	}

	public static class Java_ClassCreationExpression extends UnaryOperator
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_Type jtype;
		public @NOSPACE Java_Punctuation leftParen = new Java_Punctuation('(');
		public @NOSPACE @OPT TokenList<Java_Comment> comments;
		public @NOSPACE @OPT Java_ArgumentList argList;
		public @NOSPACE Java_Punctuation rightParen = new Java_Punctuation(')');
		public @OPT Java_ClassOverride override;

		public static class Java_ClassOverride extends TokenSequence
		{
			public Java_Punctuation leftBrace = new Java_Punctuation('{');
			public TokenList<Java_ClassElement> elementList;
			public Java_Punctuation rightBrace = new Java_Punctuation('}');
		}
	}
	
	public static class Java_ClassCreationWithInitializers extends UnaryOperator
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_Type jtype;
		public Java_Punctuation leftBrace = new Java_Punctuation('{');
		public @OPT Java_ArgumentList valueList;
		public Java_Punctuation rightBrace = new Java_Punctuation('}');
	}
	
	public static class Java_ClassCreationWithSubscript extends UnaryOperator
	{
		public Java_Keyword NEW = new Java_Keyword("new");
		public Java_Type jtype;
		public TokenList<Java_Subscript> subscripts;
	}
	
	public static class Java_MethodInvocation extends UnaryOperator implements EagleRunnable
	{
		public Java_Variable methodName;
		public @NOSPACE Java_Punctuation leftParen = new Java_Punctuation('(');
		public @NOSPACE @OPT Java_ArgumentList argList;
		public @NOSPACE Java_Punctuation rightParen = new Java_Punctuation(')');
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			// Assume System.out.println(exp);
			argList.arg.tryToInterpret(interpreter);
			EagleValue result = interpreter.popValue();
			System.out.println(result.toString());
		}
	}
	
	public static class Java_DotClass extends UnaryOperator
	{
		public Java_Type jtype;
		public @NOSPACE Java_Punctuation dot = new Java_Punctuation('.');
		public @NOSPACE Java_Keyword CLASS = new Java_Keyword("class");
	}
	
	public static class Java_ArgumentList extends UnaryOperator
	{
		public Java_Expression arg;
		public @OPT TokenList<Java_Comment> comment;
		public @OPT TokenList<Java_MoreArguments> moreArgs;
		public @OPT @CURIOUS("Extra comma") Java_Punctuation comma = new Java_Punctuation(',');
		
		public static class Java_MoreArguments extends TokenSequence
		{
			public Java_Punctuation comma = new Java_Punctuation(',');
			public @OPT TokenList<Java_Comment> comment1;
			public Java_Expression arg;
			public @OPT TokenList<Java_Comment> comment2;
		}
	}

	public static class Java_PreIncrementExpression extends UnaryOperator
	{
		public Java_Punctuation preIncrementOperator = new Java_Punctuation("++");
		public Java_Variable var;
	}

	public static class Java_PreDecrementExpression extends UnaryOperator
	{
		public Java_Punctuation preDecrementOperator = new Java_Punctuation("--");
		public Java_Variable var;
	}
	
	public static class Java_PostIncrementExpression extends UnaryOperator
	{
		public Java_Variable var;
		public Java_Punctuation postIncrementOperator = new Java_Punctuation("++");
	}

	public static class Java_PostDecrementExpression extends UnaryOperator
	{
		public Java_Variable var;
		public Java_Punctuation postDecrementOperator = new Java_Punctuation("--");
	}
	
	public static class Java_NegativeExpression extends UnaryOperator
	{
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("-", "+");
		public Java_Expression expr;
	}

	public static class Java_LogicalNotExpression extends UnaryOperator
	{
		public Java_Punctuation logicalNotOperator = new Java_Punctuation('~');
		public Java_Expression expr;
	}
	
	public static class Java_NotExpression extends UnaryOperator
	{
		public Java_Punctuation notOperator = new Java_Punctuation('!');
		public Java_Expression expr;
	}
	
	public static class Java_CommentExpression extends UnaryOperator
	{
		public Java_Comment comment;
		public Java_Expression expr;
	}


	///////////////////////////////////////////////
	// Binary expressions

	public static class Java_SubscriptExpression extends BinaryOperator
	{
		public Java_Expression expr = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_Punctuation leftBracket = new Java_Punctuation('[');
		public @OPT Java_Expression subscr;
		public Java_Punctuation rightBracket = new Java_Punctuation(']');
	}

	public static class Java_AssignmentExpression extends BinaryOperator
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

	public static class Java_TrueFalseExpression extends BinaryOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_Punctuation questionMark = new Java_Punctuation('?');
		public Java_Expression middle = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation colon = new Java_Punctuation(':');
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static class Java_ConditionalOrExpression extends BinaryOperator implements EagleRunnable
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
	
	public static class Java_ConditionalAndExpression extends BinaryOperator implements EagleRunnable
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
	
	public static class Java_InclusiveOrExpression extends BinaryOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation bitwiseOrOperator = new Java_Punctuation('|');
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Java_ExclusiveOrExpression extends BinaryOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation bitwiseXOrOperator = new Java_Punctuation('^');
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Java_AndExpression extends BinaryOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Punctuation bitwiseAndOperator = new Java_Punctuation('&');
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Java_EqualityExpression extends BinaryOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("==", "!=");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Java_RelationalExpression extends BinaryOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_PunctuationChoice operator = new Java_PunctuationChoice("<", ">", "<=", ">=");
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Java_InstanceOfExpression extends BinaryOperator
	{
		public Java_Expression expr = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Keyword instanceOperator = new Java_Keyword("instanceof");
		public Java_Type type;
	}

	public static class Java_ShiftExpression extends BinaryOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_ShiftOperator shiftOperator;
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		public static class Java_ShiftOperator extends TokenChooser
		{
			public @FIRST Java_PunctuationChoice operator = new Java_PunctuationChoice(">>>", "<<", ">>");
		}
	}

	public static class Java_AdditiveExpression extends BinaryOperator implements EagleTestable, EagleRunnable
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

	public static class Java_MultiplicativeExpression extends BinaryOperator implements EagleTestable, EagleRunnable
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

	public static class Java_Subfield extends BinaryOperator
	{
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE Java_Punctuation dot = new Java_Punctuation('.');
		public @OPT @NOSPACE Java_GenericType genericType;
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}
}
