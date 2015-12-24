// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Symbols;

import com.eagle.math.EagleValue;
import com.eagle.programmar.Template.Terminals.Template_Identifier;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.ReferenceInterface;

public class Template_Identifier_Reference extends Template_Identifier implements ReferenceInterface, EagleRunnable
{
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter._symbolTable.findSymbol(_id.toString());
		interpreter.pushEagleValue(value);
	}
}