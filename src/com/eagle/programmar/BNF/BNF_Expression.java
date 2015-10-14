// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF;

import com.eagle.programmar.BNF.Symbols.BNF_Rule_Reference;
import com.eagle.programmar.BNF.Terminals.BNF_Literal;
import com.eagle.programmar.BNF.Terminals.BNF_Punctuation;
import com.eagle.programmar.BNF.Terminals.BNF_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class BNF_Expression extends PrecedenceChooser
{
	public BNF_Expression()
	{
	}
	
	public BNF_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	protected void establishChoices() 
	{
		// Order matters a little bit ...
		super.addTerm(BNF_Rulename.class);
		super.addTerm(BNF_Literal.class);
		super.addTerm(BNF_Group.class);
		super.addTerm(BNF_Optional.class);
		
		// Order is critical ...
		super.addOperator(BNF_Alternation.class);
	}

	public static class BNF_Rulename extends ExpressionTerm
	{
		public BNF_Rule_Reference ref;
		public @NOSPACE @OPT BNF_PunctuationChoice starOrPlus = new BNF_PunctuationChoice("*", "+");
	}
	
	public static class BNF_Group extends ExpressionTerm
	{
		public PunctuationLeftParen leftParen;
		public @NOSPACE TokenList<BNF_Expression> expressions;
		public @NOSPACE PunctuationRightParen rightParen;
		public @NOSPACE @OPT BNF_PunctuationChoice starOrPlus = new BNF_PunctuationChoice("*", "+");
	}
	
	public static class BNF_Optional extends ExpressionTerm
	{
		public PunctuationLeftBracket leftBracket;
		public @NOSPACE TokenList<BNF_Expression> expressions;
		public @NOSPACE PunctuationRightBracket rightBracket;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static class BNF_Alternation extends PrecedenceOperator
	{
		public BNF_Expression left = new BNF_Expression(this, AllowedPrecedence.ATLEAST);
		public BNF_Punctuation VerticalBar = new BNF_Punctuation('|');
		public @OPT BNF_Expression right = new BNF_Expression(this, AllowedPrecedence.HIGHER);
	}
}
