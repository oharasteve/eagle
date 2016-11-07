// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.Java;

import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_Variable extends TokenSequence implements EagleRunnable
{
	public Java_VariableIdentifier var;
	public @OPT TokenList<Java_DotVariable> moreIds;
	public @OPT TokenList<Java_Subscript> subscript;

	public static class Java_DotVariable extends TokenSequence
	{
		public @NOSPACE PunctuationPeriod dot;
		public @NOSPACE Java_VariableIdentifier id;
	}
	public static class Java_VariableIdentifier extends TokenChooser
	{
		public @CHOICE Java_KeywordChoice THIS = new Java_KeywordChoice("this", "class", "super");
		public @CHOICE Java_Identifier_Reference id;
		
		public @CHOICE static class Java_CastedVariable extends TokenSequence
		{
			public PunctuationLeftParen leftParen1;
			public PunctuationLeftParen leftParen2;
			public Java_Type jtype;
			public PunctuationRightParen rightParen1;
			public Java_Identifier_Reference id;
			public PunctuationRightParen rightParen2;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Java_Identifier_Reference which = (Java_Identifier_Reference) var.getWhich();
		EagleValue value = interpreter._symbolTable.findSymbol(which.toString());
		interpreter.pushEagleValue(value);
	}
}
