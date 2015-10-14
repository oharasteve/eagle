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
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class PLI_Expression extends PrecedenceChooser
{
	public PLI_Expression()
	{
	}
	
	public PLI_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(PLI_Number.class);
		super.addTerm(PLI_BitLiteral.class);
		super.addTerm(PLI_HexNumber.class);
		super.addTerm(PLI_Literal.class);
		super.addTerm(PLI_RepeatedBitLiteral.class);
		super.addTerm(PLI_RepeatedHexLiteral.class);
		super.addTerm(PLI_RepeatedLiteral.class);
		super.addTerm(PLI_NegativeExpression.class);
		super.addTerm(PLI_NotExpression.class);
		super.addTerm(PLI_FieldReference.class);
		super.addTerm(PLI_VariableOrFunctionCall.class);
		super.addTerm(PLI_ParenthesizedExpression.class);
		super.addTerm(PLI_CommentExpression.class);
		
		// Order is critical ...
		super.addOperator(PLI_ExponentExpression.class);
		super.addOperator(PLI_MultiplicativeExpression.class);
		super.addOperator(PLI_AdditiveExpression.class);
		super.addOperator(PLI_StrCatExpression.class);
		super.addOperator(PLI_RelationalExpression.class);
		super.addOperator(PLI_AndExpression.class);
		super.addOperator(PLI_OrExpression.class);
		super.addOperator(PLI_AndThenExpression.class);
		super.addOperator(PLI_OrElseExpression.class);
	}

	///////////////////////////////////////////////
	// Primary expressions

	public static class PLI_NegativeExpression extends ExpressionTerm
	{
		public PLI_PunctuationChoice operator = new PLI_PunctuationChoice("-", "+");
		public PLI_Expression expr;
	}
	
	public static class PLI_NotExpression extends ExpressionTerm
	{
		public PLI_Punctuation notOperator = new PLI_Punctuation('^');
		public PLI_Expression expr;
	}
	
	public static class PLI_CommentExpression extends ExpressionTerm
	{
		public PLI_Comment comment;
		public PLI_Expression expr;
	}

	public static class PLI_RepeatedLiteral extends ExpressionTerm
	{
		public TokenList<PLI_RepeatCount> repeat;
		public PLI_Literal literal;
		
		public static class PLI_RepeatCount extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public PLI_Number count;
			public PunctuationRightParen rightParen;
		}
	}
	
	public static class PLI_RepeatedBitLiteral extends ExpressionTerm
	{
		public TokenList<PLI_RepeatCount> repeat;
		public PLI_BitLiteral literal;
	}
	
	public static class PLI_RepeatedHexLiteral extends ExpressionTerm
	{
		public TokenList<PLI_RepeatCount> repeat;
		public PLI_HexNumber literal;
	}
	
	public static class PLI_ParenthesizedExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public PLI_Expression expr;
		public @OPT PLI_Expression_Do expressionDo;
		public PunctuationRightParen rightParen;
		
		public static class PLI_Expression_Do extends TokenSequence
		{
			public PLI_Keyword DO = new PLI_Keyword("DO");
			public PLI_Variable_Definition var;
			public PunctuationEquals equals;
			public PLI_Expression start;
			public PLI_Keyword TO = new PLI_Keyword("TO");
			public PLI_Expression stop;
		}
	}
	
	public static class PLI_VariableOrFunctionCall extends ExpressionTerm
	{
		public PLI_Identifier_Reference fn;
		public @OPT PLI_Subscript subscript;
		
		public static class PLI_Subscript extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public @OPT SeparatedList<PLI_ExpressionOrStar,PunctuationComma> args;
			public PunctuationRightParen rightParen;
			
			public static class PLI_ExpressionOrStar extends TokenChooser
			{
				public PLI_Expression expr;
				public PunctuationStar star;
			}
		}
	}

	public static class PLI_FieldReference extends ExpressionTerm
	{
		public PLI_Identifier_Reference var;
		public PunctuationPeriod dot;
		public PLI_Identifier_Reference field;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static class PLI_OrElseExpression extends PrecedenceOperator
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

	public static class PLI_AndThenExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation andThen1 = new PLI_Punctuation("&:");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_OrExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice orOper = new PLI_PunctuationChoice("^", "|", "!");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_AndExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation andOper = new PLI_Punctuation('&');
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_RelationalExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice operator = new PLI_PunctuationChoice(
				"^>", "^<", "^=", "<=", ">=", ">", "<", "=");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_StrCatExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation catOper = new PLI_Punctuation("||");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_AdditiveExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice addOper = new PLI_PunctuationChoice("+", "-");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_MultiplicativeExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice multOper = new PLI_PunctuationChoice("*", "/");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class PLI_ExponentExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation exponOper = new PLI_Punctuation("**");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}
}
