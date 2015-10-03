// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF;

import com.eagle.programmar.BNF.Symbols.BNF_Rule_Reference;
import com.eagle.programmar.BNF.Terminals.BNF_Literal;
import com.eagle.programmar.BNF.Terminals.BNF_Punctuation;
import com.eagle.programmar.BNF.Terminals.BNF_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.BinaryOperator.AllowedPrecedence;
import com.eagle.tokens.TokenList;

public class BNF_Expression extends PrecedenceChooser
{
	public BNF_Expression()
	{
	}
	
	public BNF_Expression(BinaryOperator token, AllowedPrecedence allowed)
	{ 
		super(allowed, token.getClass());
	}
		
	@Override
	public void establishChoices() 
	{
		// Order matters a little bit ...
		super.addUnaryOperator(BNF_Rulename.class);
		super.addUnaryOperator(BNF_Literal.class);
		super.addUnaryOperator(BNF_Group.class);
		super.addUnaryOperator(BNF_Optional.class);
		
		// Order is critical ...
		super.addBinaryOperator(BNF_Alternation.class);
	}

	public static class BNF_Rulename extends UnaryOperator
	{
		public BNF_Rule_Reference ref;
		public @NOSPACE @OPT BNF_PunctuationChoice starOrPlus = new BNF_PunctuationChoice("*", "+");
	}
	
	public static class BNF_Group extends UnaryOperator
	{
		public BNF_Punctuation leftParen = new BNF_Punctuation("(");
		public @NOSPACE TokenList<BNF_Expression> expressions;
		public @NOSPACE BNF_Punctuation rightParen = new BNF_Punctuation(")");
		public @NOSPACE @OPT BNF_PunctuationChoice starOrPlus = new BNF_PunctuationChoice("*", "+");
	}
	
	public static class BNF_Optional extends UnaryOperator
	{
		public BNF_Punctuation leftBracket = new BNF_Punctuation("[");
		public @NOSPACE TokenList<BNF_Expression> expressions;
		public @NOSPACE BNF_Punctuation rightBracket = new BNF_Punctuation("]");
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static class BNF_Alternation extends BinaryOperator
	{
		public BNF_Expression left = new BNF_Expression(this, AllowedPrecedence.ATLEAST);
		public BNF_Punctuation VerticalBar = new BNF_Punctuation('|');
		public @OPT BNF_Expression right = new BNF_Expression(this, AllowedPrecedence.HIGHER);
	}
}
