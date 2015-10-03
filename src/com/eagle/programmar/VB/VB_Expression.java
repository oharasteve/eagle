// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.programmar.VB.Terminals.VB_Literal;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.programmar.VB.Terminals.VB_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class VB_Expression extends PrecedenceChooser
{
	public VB_Expression()
	{
	}
	
	public VB_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(VB_Number.class);			
		super.addUnaryOperator(VB_Literal.class);
		super.addUnaryOperator(VB_BuiltIn.class);
		super.addUnaryOperator(VB_FunctionCall.class);
		super.addUnaryOperator(VB_NotExpression.class);
		super.addUnaryOperator(VB_Variable.class);
		super.addUnaryOperator(VB_ParenthesizedExpression.class);
		super.addUnaryOperator(VB_CommentExpression.class);
		
		// Order is critical ...
		super.addBinaryOperator(VB_SubscriptExpression.class);
		super.addBinaryOperator(VB_Subfield.class);
		super.addBinaryOperator(VB_ExponentExpression.class);
		super.addBinaryOperator(VB_MultiplicativeExpression.class);
		super.addBinaryOperator(VB_AdditiveExpression.class);
		super.addBinaryOperator(VB_ConcatExpression.class);
		super.addBinaryOperator(VB_ShiftExpression.class);
		super.addBinaryOperator(VB_RelationalExpression.class);
		super.addBinaryOperator(VB_InstanceOfExpression.class);
		super.addBinaryOperator(VB_EqualityExpression.class);
		super.addBinaryOperator(VB_AndExpression.class);
		super.addBinaryOperator(VB_ExclusiveOrExpression.class);
		super.addBinaryOperator(VB_InclusiveOrExpression.class);
		super.addBinaryOperator(VB_ConditionalAndExpression.class);
		super.addBinaryOperator(VB_ConditionalOrExpression.class);
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static class VB_BuiltIn extends TokenChooser
	{
		public VB_KeywordChoice builtIn = new VB_KeywordChoice("false", "true", "nothing");
	}
	
	public static class VB_ParenthesizedExpression extends UnaryOperator
	{
		public VB_Punctuation leftParen = new VB_Punctuation('(');
		public VB_Expression expression;
		public VB_Punctuation rightParen = new VB_Punctuation(')');
	}
	
	public static class VB_ArgumentList extends UnaryOperator
	{
		public VB_Expression arg;
		public @OPT TokenList<VB_Comment> comment;
		public @OPT TokenList<VB_MoreArguments> moreArgs;
		public @OPT @CURIOUS("Extra comma") VB_Punctuation comma = new VB_Punctuation(',');
		
		public static class VB_MoreArguments extends TokenSequence
		{
			public VB_Punctuation comma = new VB_Punctuation(',');
			public @OPT TokenList<VB_Comment> comment1;
			public VB_Expression arg;
			public @OPT TokenList<VB_Comment> comment2;
		}
	}

	public static class VB_NegativeExpression extends UnaryOperator
	{
		public VB_PunctuationChoice operator = new VB_PunctuationChoice("-", "+");
		public VB_Expression expr;
	}

	public static class VB_NotExpression extends UnaryOperator
	{
		public VB_Keyword NOT = new VB_Keyword("NOT");
		public VB_Expression expr;
	}
	
	public static class VB_CommentExpression extends UnaryOperator
	{
		public VB_Comment comment;
		public VB_Expression expr;
	}

	public static class VB_FunctionCall extends UnaryOperator
	{
		public VB_Identifier_Reference fnName;
		public VB_Punctuation leftParen = new VB_Punctuation('(');
		public VB_Expression arg;
		public @OPT TokenList<VB_MoreArguments> more;
		public VB_Punctuation rightParen = new VB_Punctuation(')');
		
		public static class VB_MoreArguments extends TokenSequence
		{
			public VB_Punctuation comma = new VB_Punctuation(',');
			public VB_Expression arg;
		}
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class VB_ConditionalOrExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_KeywordChoice orOperator = new VB_KeywordChoice("or", "orelse");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class VB_ConditionalAndExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_KeywordChoice andOperator = new VB_KeywordChoice("and", "andalso");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class VB_InclusiveOrExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Keyword xor = new VB_Keyword("xor");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_ExclusiveOrExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation bitwiseXOrOperator = new VB_Punctuation('^');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_AndExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation bitwiseAndOperator = new VB_Punctuation('&');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_EqualityExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_EqualityOperator equalityOperator;
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

		public static class VB_EqualityOperator extends TokenChooser
		{
			public VB_Punctuation EQ = new VB_Punctuation('=');
			public VB_KeywordChoice IS = new VB_KeywordChoice("is", "like", "isnot");
		}
	}
	
	public static class VB_RelationalExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_PunctuationChoice operator = new VB_PunctuationChoice(
				"<=", ">=", "<>", "<", ">");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_InstanceOfExpression extends BinaryOperator
	{
		public VB_Expression expr = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Keyword instanceOperator = new VB_Keyword("instanceof");
		public VB_Type type;
	}

	public static class VB_ShiftExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_PunctuationChoice operator = new VB_PunctuationChoice("<<", ">>");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_ConcatExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation ampersand = new VB_Punctuation('&');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_AdditiveExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_PunctuationChoice operator = new VB_PunctuationChoice("+", "-");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_MultiplicativeExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_MultiplyOperation operator;
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

		public static class VB_MultiplyOperation extends TokenChooser
		{
			public VB_Keyword MOD = new VB_Keyword("mod");
			public VB_PunctuationChoice op = new VB_PunctuationChoice("*", "/", "%", "\\");
		}
	}

	public static class VB_ExponentExpression extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation operator = new VB_Punctuation('^');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_Subfield extends BinaryOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation dot = new VB_Punctuation('.');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_SubscriptExpression extends BinaryOperator
	{
		public VB_Expression expr = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation leftBracket = new VB_Punctuation('[');
		public VB_Expression subscr = new VB_Expression(this, AllowedPrecedence.HIGHER);
		public VB_Punctuation rightBracket = new VB_Punctuation(']');
	}
}
