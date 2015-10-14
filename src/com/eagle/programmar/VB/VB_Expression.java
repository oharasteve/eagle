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
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class VB_Expression extends PrecedenceChooser
{
	public VB_Expression()
	{
	}
	
	public VB_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(VB_Number.class);			
		super.addTerm(VB_Literal.class);
		super.addTerm(VB_BuiltIn.class);
		super.addTerm(VB_FunctionCall.class);
		super.addTerm(VB_NotExpression.class);
		super.addTerm(VB_Variable.class);
		super.addTerm(VB_ParenthesizedExpression.class);
		super.addTerm(VB_CommentExpression.class);
		
		// Order is critical ...
		super.addOperator(VB_SubscriptExpression.class);
		super.addOperator(VB_Subfield.class);
		super.addOperator(VB_ExponentExpression.class);
		super.addOperator(VB_MultiplicativeExpression.class);
		super.addOperator(VB_AdditiveExpression.class);
		super.addOperator(VB_ConcatExpression.class);
		super.addOperator(VB_ShiftExpression.class);
		super.addOperator(VB_RelationalExpression.class);
		super.addOperator(VB_InstanceOfExpression.class);
		super.addOperator(VB_EqualityExpression.class);
		super.addOperator(VB_AndExpression.class);
		super.addOperator(VB_ExclusiveOrExpression.class);
		super.addOperator(VB_InclusiveOrExpression.class);
		super.addOperator(VB_ConditionalAndExpression.class);
		super.addOperator(VB_ConditionalOrExpression.class);
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static class VB_BuiltIn extends TokenChooser
	{
		public VB_KeywordChoice builtIn = new VB_KeywordChoice("false", "true", "nothing");
	}
	
	public static class VB_ParenthesizedExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public VB_Expression expression;
		public PunctuationRightParen rightParen;
	}
	
	public static class VB_ArgumentList extends ExpressionTerm
	{
		public VB_Expression arg;
		public @OPT TokenList<VB_Comment> comment;
		public @OPT TokenList<VB_MoreArguments> moreArgs;
		public @OPT @CURIOUS("Extra comma") PunctuationComma comma;
		
		public static class VB_MoreArguments extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT TokenList<VB_Comment> comment1;
			public VB_Expression arg;
			public @OPT TokenList<VB_Comment> comment2;
		}
	}

	public static class VB_NegativeExpression extends ExpressionTerm
	{
		public VB_PunctuationChoice operator = new VB_PunctuationChoice("-", "+");
		public VB_Expression expr;
	}

	public static class VB_NotExpression extends ExpressionTerm
	{
		public VB_Keyword NOT = new VB_Keyword("NOT");
		public VB_Expression expr;
	}
	
	public static class VB_CommentExpression extends ExpressionTerm
	{
		public VB_Comment comment;
		public VB_Expression expr;
	}

	public static class VB_FunctionCall extends ExpressionTerm
	{
		public VB_Identifier_Reference fnName;
		public PunctuationLeftParen leftParen;
		public SeparatedList<VB_Expression,PunctuationComma> args;
		public PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class VB_ConditionalOrExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_KeywordChoice orOperator = new VB_KeywordChoice("or", "orelse");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class VB_ConditionalAndExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_KeywordChoice andOperator = new VB_KeywordChoice("and", "andalso");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class VB_InclusiveOrExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Keyword xor = new VB_Keyword("xor");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_ExclusiveOrExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation bitwiseXOrOperator = new VB_Punctuation('^');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_AndExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation bitwiseAndOperator = new VB_Punctuation('&');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_EqualityExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_EqualityOperator equalityOperator;
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

		public static class VB_EqualityOperator extends TokenChooser
		{
			public PunctuationEquals equals;
			public VB_KeywordChoice IS = new VB_KeywordChoice("is", "like", "isnot");
		}
	}
	
	public static class VB_RelationalExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_PunctuationChoice operator = new VB_PunctuationChoice(
				"<=", ">=", "<>", "<", ">");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_InstanceOfExpression extends PrecedenceOperator
	{
		public VB_Expression expr = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Keyword instanceOperator = new VB_Keyword("instanceof");
		public VB_Type type;
	}

	public static class VB_ShiftExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_PunctuationChoice operator = new VB_PunctuationChoice("<<", ">>");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_ConcatExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation ampersand = new VB_Punctuation('&');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_AdditiveExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_PunctuationChoice operator = new VB_PunctuationChoice("+", "-");
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_MultiplicativeExpression extends PrecedenceOperator
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

	public static class VB_ExponentExpression extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Punctuation operator = new VB_Punctuation('^');
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_Subfield extends PrecedenceOperator
	{
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationPeriod dot;
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class VB_SubscriptExpression extends PrecedenceOperator
	{
		public VB_Expression expr = new VB_Expression(this, AllowedPrecedence.ATLEAST);
		public PunctuationLeftBracket leftBracket;
		public VB_Expression subscr = new VB_Expression(this, AllowedPrecedence.HIGHER);
		public PunctuationRightBracket rightBracket;
	}
}
