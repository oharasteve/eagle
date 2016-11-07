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
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class PLI_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) PLI_Number number;
	public @P(20) PLI_BitLiteral bits;
	public @P(30) PLI_HexNumber hex;
	public @P(40) PLI_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public PLI_Expression()
	{
	    super(_operators);
	}

	public PLI_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class PLI_RepeatedBitLiteral extends PrimaryOperator
	{
		public TokenList<PLI_RepeatCount> repeat;
		public PLI_BitLiteral literal;
	}
	
	public static @P(110) class PLI_RepeatedHexLiteral extends PrimaryOperator
	{
		public TokenList<PLI_RepeatCount> repeat;
		public PLI_HexNumber literal;
	}
	
	public static @P(120) class PLI_RepeatedLiteral extends PrimaryOperator
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
	
	public static @P(130) class PLI_NegativeExpression extends PrimaryOperator
	{
		public PLI_PunctuationChoice operator = new PLI_PunctuationChoice("-", "+");
		public PLI_Expression expr;
	}
	
	public static @P(140) class PLI_NotExpression extends PrimaryOperator
	{
		public PLI_Punctuation notOperator = new PLI_Punctuation('^');
		public PLI_Expression expr;
	}
	
	public static @P(150) class PLI_FieldReference extends PrimaryOperator
	{
		public PLI_Identifier_Reference var;
		public PunctuationPeriod dot;
		public PLI_Identifier_Reference field;
	}
	
	public static @P(160) class PLI_VariableOrFunctionCall extends PrimaryOperator
	{
		public PLI_Identifier_Reference fn;
		public @OPT PLI_Subscript subscript;
	}

	public static @P(170) class PLI_ParenthesizedExpression extends PrimaryOperator
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
	
	public static @P(180) class PLI_CommentExpression extends PrimaryOperator
	{
		public PLI_Comment comment;
		public PLI_Expression expr;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(190) class PLI_ExponentExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation exponOper = new PLI_Punctuation("**");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(200) class PLI_MultiplicativeExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice multOper = new PLI_PunctuationChoice("*", "/");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(210) class PLI_AdditiveExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice addOper = new PLI_PunctuationChoice("+", "-");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(220) class PLI_StrCatExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation catOper = new PLI_Punctuation("||");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(230) class PLI_RelationalExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice operator = new PLI_PunctuationChoice(
				"^>", "^<", "^=", "<=", ">=", ">", "<", "=");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(240) class PLI_AndExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation andOper = new PLI_Punctuation('&');
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(250) class PLI_OrExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_PunctuationChoice orOper = new PLI_PunctuationChoice("^", "|", "!");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(260) class PLI_AndThenExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Punctuation andThen1 = new PLI_Punctuation("&:");
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(270) class PLI_OrElseExpression extends PrecedenceOperator
	{
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_OrElseOperator orElseOper;
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);

		public static class PLI_OrElseOperator extends TokenChooser
		{
			public @CHOICE PLI_Punctuation orElse1 = new PLI_Punctuation("!:");
			public @CHOICE PLI_Punctuation orElse2 = new PLI_Punctuation("|:");
		}
	}
}
