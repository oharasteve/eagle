// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.programmar.TCL.Terminals.TCL_Literal;
import com.eagle.programmar.TCL.Terminals.TCL_Number;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class TCL_Expression extends PrecedenceChooser
{
	public TCL_Expression()
	{
	}
	
	public TCL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(TCL_Number.class);			
		super.addTerm(TCL_Literal.class);
		super.addTerm(TCL_Variable.class);
		super.addTerm(TCL_SignedExpression.class);
		super.addTerm(TCL_NotExpression.class);
		super.addTerm(TCL_BangExpression.class);
		super.addTerm(TCL_ParenthesizedExpression.class);
		
		// Order is critical ...
		super.addOperator(TCL_MultiplicativeExpression.class);
		super.addOperator(TCL_AdditiveExpression.class);
		super.addOperator(TCL_RelationalExpression.class);
		super.addOperator(TCL_ConditionalAndExpression.class);
		super.addOperator(TCL_ConditionalOrExpression.class);
	}
	
	///////////////////////////////////////////////
	// Primary expressions

	public static class TCL_SignedExpression extends ExpressionTerm
	{
		public TCL_PunctuationChoice signedOperator = new TCL_PunctuationChoice("+", "-");
		public TCL_Expression expr;
	}

	public static class TCL_NotExpression extends ExpressionTerm
	{
		public TCL_Keyword NOT = new TCL_Keyword("not");
		public TCL_Expression expr;
	}
	
	public static class TCL_BangExpression extends ExpressionTerm
	{
		public TCL_Punctuation bang = new TCL_Punctuation('!');
		public TCL_Expression expr;
	}
	
	public static class TCL_ParenthesizedExpression extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public TCL_Expression expression;
		public PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static class TCL_ConditionalOrExpression extends PrecedenceOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_Keyword OR = new TCL_Keyword("or");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static class TCL_ConditionalAndExpression extends PrecedenceOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_Keyword AND = new TCL_Keyword("and");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
		
	public static class TCL_RelationalExpression extends PrecedenceOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_PunctuationChoice operator = new TCL_PunctuationChoice(
				"<", ">", "<=", ">=", "==", "<>");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
		
	public static class TCL_AdditiveExpression extends PrecedenceOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_PunctuationChoice operator = new TCL_PunctuationChoice("+", "-");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static class TCL_MultiplicativeExpression extends PrecedenceOperator
	{
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_PunctuationChoice operator = new TCL_PunctuationChoice("*", "/");
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}
}
