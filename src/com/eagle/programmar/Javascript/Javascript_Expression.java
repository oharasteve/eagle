// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Javascript_Expression.Javascript_ParenthesizedExpression.Javascript_MoreExpressions;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_HexNumber;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_RegularExpression;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Javascript_Expression extends PrecedenceChooser
{
	private boolean _allowComma = true;
	
	public Javascript_Expression(boolean allowComma)
	{
		_allowComma = allowComma;
	}
	
	public Javascript_Expression()
	{
	}
	
	public Javascript_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(Javascript_HexNumber.class);
		super.addUnaryOperator(Javascript_Number.class);
		super.addUnaryOperator(Javascript_Literal.class);
		super.addUnaryOperator(Javascript_RegularExpression.class);
		super.addUnaryOperator(Javascript_DotClass.class);
		super.addUnaryOperator(Javascript_CastExpression.class);
		super.addUnaryOperator(Javascript_ExpressionList.class);
		super.addUnaryOperator(Javascript_StringFunction.class);
		super.addUnaryOperator(Javascript_DeleteExpression.class);
		super.addUnaryOperator(Javascript_ClassCreationExpression.class);
		super.addUnaryOperator(Javascript_ClassCreationWithInitializers.class);
		super.addUnaryOperator(Javascript_ClassCreationWithSubscript.class);
		super.addUnaryOperator(Javascript_NewNoArgsExpression.class);
		super.addUnaryOperator(Javascript_MethodInvocation.class);
		super.addUnaryOperator(Javascript_PreIncrementExpression.class);
		super.addUnaryOperator(Javascript_PreDecrementExpression.class);
		super.addUnaryOperator(Javascript_PostIncrementExpression.class);
		super.addUnaryOperator(Javascript_PostDecrementExpression.class);
		super.addUnaryOperator(Javascript_NegativeExpression.class);
		super.addUnaryOperator(Javascript_LogicalNotExpression.class);
		super.addUnaryOperator(Javascript_NotExpression.class);
		super.addUnaryOperator(Javascript_BuiltIn.class);
		super.addUnaryOperator(Javascript_Variable.class);
		super.addUnaryOperator(Javascript_StrangeFunction.class);
		super.addUnaryOperator(Javascript_ParenthesizedFunction.class);
		super.addUnaryOperator(Javascript_ParenthesizedExpression.class);
		super.addUnaryOperator(Javascript_SimpleArray.class);
		super.addUnaryOperator(Javascript_CommentExpression.class);
		super.addUnaryOperator(Javascript_Dictionary.class);
		super.addUnaryOperator(Javascript_FunctionExpr.class);
		super.addUnaryOperator(Javascript_TypeOfExpr.class);
		super.addUnaryOperator(Javascript_VoidExpr.class);
		
		// Order is critical ...
		super.addBinaryOperator(Javascript_SubscriptExpression.class);
		super.addBinaryOperator(Javascript_Subfield.class);
		super.addBinaryOperator(Javascript_MultiplicativeExpression.class);
		super.addBinaryOperator(Javascript_AdditiveExpression.class);
		super.addBinaryOperator(Javascript_ShiftExpression.class);
		super.addBinaryOperator(Javascript_RelationalExpression.class);
		super.addBinaryOperator(Javascript_InstanceOfExpression.class);
		super.addBinaryOperator(Javascript_InExpression.class);
		super.addBinaryOperator(Javascript_EqualityExpression.class);
		super.addBinaryOperator(Javascript_AndExpression.class);
		super.addBinaryOperator(Javascript_ExclusiveOrExpression.class);
		super.addBinaryOperator(Javascript_InclusiveOrExpression.class);
		super.addBinaryOperator(Javascript_ConditionalAndExpression.class);
		super.addBinaryOperator(Javascript_ConditionalOrExpression.class);
		super.addBinaryOperator(Javascript_AssignmentExpression.class);
		super.addBinaryOperator(Javascript_TrueFalseExpression.class);
		if (_allowComma)
		{
			super.addBinaryOperator(Javascript_CommaExpression.class);
		}
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static class Javascript_BuiltIn extends TokenChooser
	{
		public Javascript_KeywordChoice logicalConstant = new Javascript_KeywordChoice("false", "true", "null", "this", "super");
		public Javascript_Keyword STRING = new Javascript_Keyword("String");
	}
	
	public static class Javascript_ParenthesizedExpression extends UnaryOperator
	{
		public Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
		public @OPT @NOSPACE Javascript_Expression expression;
		public @OPT TokenList<Javascript_MoreExpressions> moreExpr;
		public @NOSPACE Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
		
		public static class Javascript_MoreExpressions extends TokenSequence
		{
			public @NOSPACE Javascript_Punctuation comma = new Javascript_Punctuation(',');
			public @OPT Javascript_Expression nextExpr;
		}
	}
	
	public static class Javascript_StrangeFunction extends UnaryOperator
	{
		public Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
		public @NOSPACE Javascript_Number zero;
		public @NOSPACE Javascript_Punctuation comma = new Javascript_Punctuation(',');
		public Javascript_Variable function;
		public @NOSPACE Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
		public @NEWLINE Javascript_ParenthesizedExpression arguments;
	}

	public static class Javascript_ParenthesizedFunction extends UnaryOperator
	{
		public @INDENT Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
		public Javascript_Function function;
		public @OUTDENT Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
		public Javascript_ParenthesizedExpression arguments;
	}

	public static class Javascript_SimpleArray extends UnaryOperator
	{
		public Javascript_Punctuation leftBracket = new Javascript_Punctuation('[');
		public @OPT Javascript_Expression expr;
		public @OPT TokenList<Javascript_MoreExpressions> moreExpr;
		public Javascript_Punctuation rightBracket = new Javascript_Punctuation(']');
	}
	
	public static class Javascript_CastExpression extends UnaryOperator
	{
		public Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
		public @NOSPACE Javascript_Type jtype;
		public @NOSPACE Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
		public Javascript_Expression expr;
	}
	
	public static class Javascript_DeleteExpression extends UnaryOperator
	{
		public Javascript_Keyword DELETE = new Javascript_Keyword("delete");
		public Javascript_Expression expr;
	}

	public static class Javascript_ExpressionList extends UnaryOperator
	{
		public Javascript_Punctuation leftBrace = new Javascript_Punctuation('{');
		public @OPT @NOSPACE TokenList<Javascript_Comment> comment;
		public @OPT @NOSPACE Javascript_ArgumentList valueList;
		public @NOSPACE Javascript_Punctuation rightBrace = new Javascript_Punctuation('}');
	}
	
	public static class Javascript_NewNoArgsExpression extends UnaryOperator
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
	}
	
	public static class Javascript_ClassCreationExpression extends UnaryOperator
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
		public Javascript_ParenthesizedExpression arguments;
	}
	
	public static class Javascript_ClassCreationWithInitializers extends UnaryOperator
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
		public Javascript_Punctuation leftBrace = new Javascript_Punctuation('{');
		public Javascript_ArgumentList valueList;
		public Javascript_Punctuation rightBrace = new Javascript_Punctuation('}');
	}
	
	public static class Javascript_ClassCreationWithSubscript extends UnaryOperator
	{
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
		public Javascript_Type jtype;
		public TokenList<Javascript_Subscript> subscripts;
	}

	public static class Javascript_MethodInvocation extends UnaryOperator
	{
		public Javascript_Variable methodName;
		public @NOSPACE Javascript_ParenthesizedExpression arguments;
	}
	
	public static class Javascript_DotClass extends UnaryOperator
	{
		public Javascript_Type jtype;
		public Javascript_Punctuation dot = new Javascript_Punctuation('.');
		public Javascript_Keyword CLASS = new Javascript_Keyword("class");
	}
	
	public static class Javascript_ArgumentList extends UnaryOperator
	{
		public Javascript_Expression arg;
		public @OPT TokenList<Javascript_Comment> comment;
		public @OPT TokenList<Javascript_MoreArguments> moreArgs;
		public @OPT @CURIOUS("Extra comma") Javascript_Punctuation comma = new Javascript_Punctuation(',');
		
		public static class Javascript_MoreArguments extends TokenSequence
		{
			public @NOSPACE Javascript_Punctuation comma = new Javascript_Punctuation(',');
			public @OPT TokenList<Javascript_Comment> comment1;
			public Javascript_Expression arg;
			public @OPT TokenList<Javascript_Comment> comment2;
		}
	}

	public static class Javascript_PreIncrementExpression extends UnaryOperator
	{
		public Javascript_Punctuation preIncrementOperator = new Javascript_Punctuation("++");
		public @NOSPACE Javascript_Variable var;
	}

	public static class Javascript_PreDecrementExpression extends UnaryOperator
	{
		public Javascript_Punctuation preDecrementOperator = new Javascript_Punctuation("--");
		public @NOSPACE Javascript_Variable var;
	}
	
	public static class Javascript_PostIncrementExpression extends UnaryOperator
	{
		public Javascript_Variable var;
		public @NOSPACE Javascript_Punctuation postIncrementOperator = new Javascript_Punctuation("++");
	}

	public static class Javascript_PostDecrementExpression extends UnaryOperator
	{
		public Javascript_Variable var;
		public @NOSPACE Javascript_Punctuation postDecrementOperator = new Javascript_Punctuation("--");
	}
	
	public static class Javascript_NegativeExpression extends UnaryOperator
	{
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("-", "+");
		public Javascript_Expression expr;
	}

	public static class Javascript_LogicalNotExpression extends UnaryOperator
	{
		public Javascript_Punctuation logicalNotOperator = new Javascript_Punctuation('~');
		public Javascript_Expression expr;
	}
	
	public static class Javascript_NotExpression extends UnaryOperator
	{
		public Javascript_Punctuation notOperator = new Javascript_Punctuation('!');
		public Javascript_Expression expr;
	}
	
	public static class Javascript_CommentExpression extends UnaryOperator
	{
		public Javascript_Comment comment;
		public Javascript_Expression expr;
	}
	
	public static class Javascript_Dictionary extends UnaryOperator
	{
		// Don't use @INDENT here. Messes up 'return' statements that return a dictionary.
		public Javascript_Punctuation leftBrace = new Javascript_Punctuation('{');
		public Javascript_DictionaryItem firstItem;
		public @OPT TokenList<Javascript_MoreDictionaryItems> moreItems;
		public @OPT Javascript_Punctuation comma = new Javascript_Punctuation(',');
		public @OPT Javascript_Comment comment;
		public Javascript_Punctuation rightBrace = new Javascript_Punctuation('}');
		
		public static class Javascript_DictionaryItem extends TokenSequence
		{
			public Javascript_Expression key;
			public Javascript_Punctuation colon = new Javascript_Punctuation(':');
			public Javascript_Expression value = new Javascript_Expression(false);
		}
		
		public static class Javascript_MoreDictionaryItems extends TokenSequence
		{
			public @NOSPACE Javascript_Punctuation comma = new Javascript_Punctuation(',');
			public Javascript_DictionaryItem nextItem;
		}
	}

	public static class Javascript_FunctionExpr extends UnaryOperator
	{
		public Javascript_Function function;
		public @OPT Javascript_ParenthesizedExpression args;
	}
	
	public static class Javascript_TypeOfExpr extends UnaryOperator
	{
		public Javascript_Keyword TYPEOF = new Javascript_Keyword("typeof");
		public Javascript_Expression expr;
	}
	
	public static class Javascript_VoidExpr extends UnaryOperator
	{
		public Javascript_Keyword VOID = new Javascript_Keyword("void");
		public Javascript_Number number;
	}
	
	public static class Javascript_StringFunction extends UnaryOperator
	{
		public Javascript_Keyword STRING = new Javascript_Keyword("String");
		public Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
		public Javascript_Expression expr;
		public Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class Javascript_CommaExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE Javascript_Punctuation comma = new Javascript_Punctuation(',');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Javascript_AssignmentExpression extends BinaryOperator
	{
		public Javascript_Expression var = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	}

	public static class Javascript_TrueFalseExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public Javascript_Punctuation questionMark = new Javascript_Punctuation('?');
		public Javascript_Expression middle = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation colon = new Javascript_Punctuation(':');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static class Javascript_ConditionalOrExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation orOperator = new Javascript_Punctuation("||");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Javascript_ConditionalAndExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation andOperator = new Javascript_Punctuation("&&");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class Javascript_InclusiveOrExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation bitwiseOrOperator = new Javascript_Punctuation('|');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_ExclusiveOrExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation bitwiseXOrOperator = new Javascript_Punctuation('^');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_AndExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Punctuation bitwiseAndOperator = new Javascript_Punctuation('&');
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_EqualityExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("!==", "===", "==", "!=");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public @OPT Javascript_Comment comment;
	}
	
	public static class Javascript_RelationalExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("<", ">", "<=", ">=");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_InstanceOfExpression extends BinaryOperator
	{
		public Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Keyword instanceOperator = new Javascript_Keyword("instanceof");
		public Javascript_Type type;
	}

	public static class Javascript_InExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Keyword inOperator = new Javascript_Keyword("in");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_ShiftExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice(">>>", "<<", ">>");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_AdditiveExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("+", "-");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_MultiplicativeExpression extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("*", "/", "%");
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_Subfield extends BinaryOperator
	{
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE Javascript_Punctuation dot = new Javascript_Punctuation('.');
		public @NOSPACE Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class Javascript_SubscriptExpression extends BinaryOperator
	{
		public Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @NOSPACE Javascript_Punctuation leftBracket = new Javascript_Punctuation('[');
		public @NOSPACE Javascript_Expression subscr = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public @NOSPACE Javascript_Punctuation rightBracket = new Javascript_Punctuation(']');
	}
}
