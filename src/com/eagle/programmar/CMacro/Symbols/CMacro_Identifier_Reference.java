// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.CMacro.Symbols;

import com.eagle.math.EagleValue;
import com.eagle.math.StringValue;
import com.eagle.programmar.CMacro.Terminals.CMacro_Identifier;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.ReferenceInterface;

public class CMacro_Identifier_Reference extends CMacro_Identifier implements ReferenceInterface, EagleRunnable
{
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter._symbolTable.findSymbol(_id.toString());
		if (value == null) value = new StringValue("");		// Treat undefined symbols as blank
		interpreter.pushEagleValue(value);
	}
}
