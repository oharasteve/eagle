// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.PLI_Expression.PLI_RepeatedLiteral.PLI_RepeatCount;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_BitLiteral;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_HexNumber;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.programmar.PLI.Terminals.PLI_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_Expression extends PrecedenceChooser
{
	public PLI_Expression()
	{
	}
	
	public PLI_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(PLI_Number.class);
		super.addUnaryOperator(PLI_BitLiteral.class);
		super.addUnaryOperator(PLI_HexNumber.class);
		super.addUnaryOperator(PLI_Literal.class);
		super.addUnaryOperator(PLI_RepeatedBitLiteral.class);
		super.addUnaryOperator(PLI_RepeatedHexLiteral.class);
		super.addUnaryOperator(PLI_RepeatedLiteral.class);
		super.addUnaryOperator(PLI_NegativeExpression.class);
		super.addUnaryOperator(PLI_NotExpression.class);
		super.addUnaryOperator(PLI_FieldReference.class);
		super.addUnaryOperator(PLI_VariableOrFunctionCall.class);
		super.addUnaryOperator(PLI_ParenthesizedExpression.class);
		super.addUnaryOperator(PLI_CommentExpression.class);
		
		// Order is critical ...
		super.addBinaryOperator(PLI_ExponentExpression.class);
		super.addBinaryOperator(PLI_MultiplicativeExpression.class);
		super.addBinaryOperator(PLI_AdditiveExpression.class);
		super.addBinaryOperator(PLI_StrCatExpression.class);
		super.addBinaryOperator(PLI_RelationalExpression.class);
		super.addBinaryOperator(PLI_AndExpression.class);
		super.addBinaryOperator(PLI_OrExpression.class);
		super.addBinaryOperator(PLI_AndThenExpression.class);
		super.addBinaryOperator(PLI_OrElseExpression.class);
	}

	///////////////////////////////////////////////
	// Primary expressions

	public static class PLI_NegativeExpression extends UnaryOperator
	{
		public PLI_PunctuationChoice operator = new PLI_PunctuationChoice("-", "+");
		public PLI_Expression expr;
	}
	
	public static class PLI_NotExpression extends UnaryOperator
	{
		public PLI_Punctuation notOperator = new PLI_Punctuation('^');
		public PLI_Expression expr;
	}
	
	public static class PLI_CommentExpression extends UnaryOperator
	{
		public PLI_Comment comment;
		public PLI_Expression expr;
	}

	public static class PLI_RepeatedLiteral extends UnaryOperator
	{
		public TokenList<PLI_RepeatCount> repeat;
		public PLI_Literal literal;
		
		public static class PLI_RepeatCount extends TokenSequence
		{
			public PLI_Punctuation leftParen = new PLI_Punctuation('(');
			public PLI_Number count;
			public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		}
	}
	
	public static class PLI_RepeatedBitLiteral extends UnaryOperator
	{
		public TokenList<PLI_RepeatCount> repeat;
		public PLI_BitLiteral literal;
	}
	
	public static class PLI_RepeatedHexLiteral extends UnaryOperator
	{
		public TokenList<PLI_RepeatCount> repeat;
		public PLI_HexNumber literal;
	}
	
	public static class PLI_ParenthesizedExpression extends UnaryOperator
	{
		public PLI_Punctuation leftParen = new PLI_Punctuation('(');
		public PLI_Expression expr;
		public @OPT PLI_Expression_Do expressionDo;
		public PLI_Punctuation rightParen = new PLI_Punctuation(')');
		
		public static class PLI_Expression_Do extends TokenSequence
		{
			public PLI_Keyword DO = new PLI_Keyword("DO");
			public PLI_Variable_Definition var;
			public PLI_Punctuation equals = new PLI_Punctuation('=');
			public PLI_Expression start;
			public PLI_Keyword TO = new PLI_Keyword("TO");
			public PLI_Expression stop;
		}
	}
	
	public static class PLI_VariableOrFunctionCall extends UnaryOperator
	{
		public PLI_Identifier_Reference fn;
		public @OPT PLI_Subscript subscript;
		
		public static class PLI_Subscript extends TokenSequence
		{
			public PLI_Punctuation leftParen = new PLI_Punctuation('(');
			public @OPT PLI_ExpressionOrStar arg;
			public @OPT TokenList<PLI_MoreFunctionArgs> moreArgs;
			public PLI_Punctuation rightParen = new PLI_Punctuation(')');
			
			public static class PLI_MoreFunctionArgs extends TokenSequence
			{
				public PLI_Punctuation comma = new PLI_Punctuation(',');
				public PLI_ExpressionOrStar arg;
			}
			
			public static class PLI_ExpressionOrStar extends TokenChooser
			{
				public PLI_Expression expr;
				public PLI_Punctuation star = new PLI_Punctuation('*');
			}
		}
	}

	public static class PLI_FieldReference extends UnaryOperator
	{
		public PLI_Identifier_Reference var;
		public PLI_Punctuation dot = new PLI_Punctuation('.');
		public PLI_Identifier_Reference field;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static class PLI_OrElseExpression extends BinaryOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_OrElseOperator orElseOper;
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);

		public static class PLI_OrElseOperator extends TokenChooser
		{
			public PLI_Punctuation orElse1 = new PLI_Punctuation("!:");
			public PLI_Punctuation orElse2 = new PLI_Punctuation("|:");
		}
	}

	public static class PLI_AndThenExpression extends BinaryOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation andThen1 = new PLI_Punctuation("&:");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_OrExpression extends BinaryOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice orOper = new PLI_PunctuationChoice("^", "|", "!");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_AndExpression extends BinaryOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation andOper = new PLI_Punctuation('&');
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_RelationalExpression extends BinaryOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice operator = new PLI_PunctuationChoice(
				"^>", "^<", "^=", "<=", ">=", ">", "<", "=");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_StrCatExpression extends BinaryOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation catOper = new PLI_Punctuation("||");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_AdditiveExpression extends BinaryOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice addOper = new PLI_PunctuationChoice("+", "-");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_MultiplicativeExpression extends BinaryOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice multOper = new PLI_PunctuationChoice("*", "/");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_ExponentExpression extends BinaryOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation exponOper = new PLI_Punctuation("**");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}
}
