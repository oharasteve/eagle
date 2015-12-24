// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.programmar.Template.Statements;

import com.eagle.math.EagleValue;
import com.eagle.programmar.Template.Template_Expression;
import com.eagle.programmar.Template.Template_Variable;
import com.eagle.programmar.Template.Symbols.Template_Identifier_Definition;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Template_AssignmentStatement extends TokenSequence implements EagleRunnable
{
	public @NEWLINE Template_Variable var;
	public PunctuationEquals equals;
	public Template_Expression expr;
	public @NOSPACE PunctuationSemicolon semicolon;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		Template_Identifier_Definition id = (Template_Identifier_Definition) var._whichToken;
		interpreter._symbolTable.setSymbol(id.getValue(), value);
	}
}
