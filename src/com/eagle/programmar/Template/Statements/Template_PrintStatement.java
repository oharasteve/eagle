// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Statements;

import com.eagle.math.EagleValue;
import com.eagle.programmar.Template.Template_Expression;
import com.eagle.programmar.Template.Terminals.Template_Keyword;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Template_PrintStatement extends TokenSequence implements EagleRunnable
{
	public @NEWLINE Template_Keyword PRINT = new Template_Keyword("print");
	public Template_Expression expr;
	public @NOSPACE PunctuationSemicolon semicolon;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue result = interpreter.getEagleValue(expr);
		System.out.println(result.toString());
	}
}
