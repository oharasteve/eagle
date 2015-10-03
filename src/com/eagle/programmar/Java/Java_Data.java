// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

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
	public @NOSPACE Java_Punctuation semicolon = new Java_Punctuation(';');
	
	public static class Java_DataSubscript extends TokenSequence
	{
		public Java_Punctuation leftBracket = new Java_Punctuation('[');
		public Java_Punctuation rightBracket = new Java_Punctuation(']');
	}
	
	public static class Java_DataModifier extends TokenSequence
	{
		public Java_KeywordChoice modifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
	}
	
	public static class Java_DataInitialValue extends TokenSequence
	{
		public Java_Punctuation equals = new Java_Punctuation('=');
		public Java_Expression expression;
	}
	
	public static class Java_MoreIdentifiers extends TokenSequence
	{
		public Java_Punctuation comma = new Java_Punctuation(',');
		public Java_Variable_Definition id;
		public @OPT Java_Punctuation leftBracket = new Java_Punctuation('[');
		public @OPT Java_Punctuation rightBracket = new Java_Punctuation(']');
		public @OPT Java_DataInitialValue initialValue;
	}

	@Override public void interpret(EagleInterpreter interpreter)
	{
		interpreter.setValue(id, 5);
	}
}
