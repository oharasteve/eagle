// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_ExpressionStatement extends TokenSequence implements EagleRunnable
{
	public @NEWLINE Java_Expression expr;
	public @NOSPACE PunctuationSemicolon semicolon;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}
