// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2011

package com.eagle.programmar.Java.Symbols;

import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;

public class Java_Variable_Definition extends Java_Identifier_Definition implements EagleRunnable
{
	@Override
	public String typeName()
	{
		return "Variable";
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter._symbolTable.findSymbol(this.toString()).forceIntegerValue();
		interpreter.pushInt(val);
	}
}
