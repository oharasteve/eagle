// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TokenSequence;

public class Java_ExpressionStatement extends TokenSequence implements EagleRunnable
{
	public @NEWLINE Java_Expression expr;
	public @NOSPACE Java_Punctuation semicolon = new Java_Punctuation(';');
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		expr.tryToInterpret(interpreter);
	}
}
