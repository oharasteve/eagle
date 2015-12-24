// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Data extends TokenSequence implements EagleRunnable
{
	public @OPT @NEWLINE TokenList<Java_Annotation> annotation1;
	public @OPT TokenList<Java_DataModifier> modifiers;
	public @OPT TokenList<Java_Annotation> annotation2;
	public Java_Type jtype;
	public Java_Variable_Definition id;
	public @OPT TokenList<Java_DataSubscript> subscripts;
	public @OPT Java_DataInitialValue initialValue;
	public @OPT TokenList<Java_MoreIdentifiers> moreIds;
	public @NOSPACE PunctuationSemicolon semicolon;
	
	public static class Java_DataSubscript extends TokenSequence
	{
		public PunctuationLeftBracket leftBracket;
		public PunctuationRightBracket rightBracket;
	}
	
	public static class Java_DataModifier extends TokenSequence
	{
		public Java_KeywordChoice modifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
	}
	
	public static class Java_DataInitialValue extends TokenSequence implements EagleRunnable
	{
		public PunctuationEquals equals;
		public Java_Expression expression;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(expression);
			interpreter.pushEagleValue(value);
		}
	}
	
	public static class Java_MoreIdentifiers extends TokenSequence
	{
		public PunctuationComma comma;
		public Java_Variable_Definition id;
		public @OPT PunctuationLeftBracket leftBracket;
		public @OPT PunctuationRightBracket rightBracket;
		public @OPT Java_DataInitialValue initialValue;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(initialValue);
		interpreter._symbolTable.setSymbol(id.toString(), value);
	}
}
