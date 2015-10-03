// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

package com.eagle.programmar.CMacro;

import com.eagle.programmar.C.Terminals.C_Character_Literal;
import com.eagle.programmar.C.Terminals.C_HexNumber;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;

public class CMacro_Expression extends PrecedenceChooser
{
	public CMacro_Expression()
	{
	}
	
	public CMacro_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(C_Number.class);
		super.addUnaryOperator(C_HexNumber.class);
		super.addUnaryOperator(C_Literal.class);
		super.addUnaryOperator(C_Character_Literal.class);
		super.addUnaryOperator(CMacro_FunctionCall.class);
		super.addUnaryOperator(CMacro_Identifier_Reference.class);
		super.addUnaryOperator(CMacro_NotExpression.class);
		super.addUnaryOperator(CMacro_ParenthesizedExpression.class);
		super.addUnaryOperator(CMacro_SymbolExpression.class);
		
		// Order is critical ...
		super.addBinaryOperator(CMacro_MultiplicativeExpression.class);
		super.addBinaryOperator(CMacro_AdditiveExpression.class);
		super.addBinaryOperator(CMacro_RelationalExpression.class);
		super.addBinaryOperator(CMacro_EqualityExpression.class);
		super.addBinaryOperator(CMacro_BitwiseAndExpression.class);
		super.addBinaryOperator(CMacro_ExclusiveOrExpression.class);
		super.addBinaryOperator(CMacro_BitwiseOrExpression.class);
		super.addBinaryOperator(CMacro_ConditionalAndExpression.class);
		super.addBinaryOperator(CMacro_ConditionalOrExpression.class);
		super.addBinaryOperator(CMacro_ConcatenateExpression.class);
	}
	
	///////////////////////////////////////////////
	// Primary expressions

	public static class CMacro_SymbolExpression extends UnaryOperator
	{
		public C_Punctuation poundOperator = new C_Punctuation('#');
		public CMacro_Expression expr;
	}
	
	public static class CMacro_NotExpression extends UnaryOperator
	{
		public C_Punctuation notOperator = new C_Punctuation('!');
		public CMacro_Expression expr;
	}
	
	public static class CMacro_ParenthesizedExpression extends UnaryOperator
	{
		public C_Punctuation leftParen = new C_Punctuation('(');
		public CMacro_Expression expression;
		public C_Punctuation rightParen = new C_Punctuation(')');
	}

	public static class CMacro_FunctionCall extends UnaryOperator
	{
		public C_Keyword DEFINED = new C_Keyword("defined");
		public C_Punctuation leftParen = new C_Punctuation('(');
		public CMacro_Identifier_Reference variable;
		public C_Punctuation rightParen = new C_Punctuation(')');
	}
				
	///////////////////////////////////////////////
	// Binary expressions

	public static class CMacro_ConcatenateExpression extends BinaryOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation poundOperator = new C_Punctuation("##");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class CMacro_ConditionalOrExpression extends BinaryOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation orOperator = new C_Punctuation("||");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class CMacro_ConditionalAndExpression extends BinaryOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation andOperator = new C_Punctuation("&&");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}
		
	public static class CMacro_BitwiseOrExpression extends BinaryOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseOrOperator = new C_Punctuation('|');
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CMacro_ExclusiveOrExpression extends BinaryOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseXOrOperator = new C_Punctuation('^');
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CMacro_BitwiseAndExpression extends BinaryOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Punctuation bitwiseAndOperator = new C_Punctuation('&');
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CMacro_EqualityExpression extends BinaryOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("==", "!=");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class CMacro_RelationalExpression extends BinaryOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("<", ">", "<=", ">=");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CMacro_AdditiveExpression extends BinaryOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("+", "-");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class CMacro_MultiplicativeExpression extends BinaryOperator
	{
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public C_PunctuationChoice operator = new C_PunctuationChoice("*", "/", "%");
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}
}
