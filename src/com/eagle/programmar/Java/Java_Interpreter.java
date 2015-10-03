// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2013

package com.eagle.programmar.Java;

import com.eagle.programmar.DefinitionInterface;
import com.eagle.programmar.ReferenceInterface;
import com.eagle.programmar.Java.Java_Data.Java_DataInitialValue;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.tests.EagleInterpreter;


public class Java_Interpreter extends EagleInterpreter
{
	@Override
	public int getValue(ReferenceInterface variable)
	{
		Java_Variable_Definition jdefinition = (Java_Variable_Definition) variable.getDefinition();
		if (jdefinition.getParent() instanceof Java_Data)
		{
			Java_Data data = (Java_Data) jdefinition.getParent();
			Java_DataInitialValue init = data.initialValue;
			if (init != null)
			{
				Java_Expression expr = init.expression;
				if (expr._whichToken instanceof Java_Number)
				{
					Java_Number num = (Java_Number) expr._whichToken;
					return Integer.parseInt(num.getValue());
				}
			}
		}
		throw new RuntimeException("Unable to find a value for " + variable);
	}
	
	@Override
	public void setValue(DefinitionInterface definition, int value)
	{
		Java_Variable_Definition jdefinition = (Java_Variable_Definition) definition;
		if (jdefinition.getParent() instanceof Java_Data)
		{
			Java_Data data = (Java_Data) jdefinition.getParent();
			Java_DataInitialValue init = data.initialValue;
			if (init != null)
			{
				Java_Expression expr = init.expression;
				if (expr._whichToken instanceof Java_Number)
				{
					Java_Number num = (Java_Number) expr._whichToken;
					num.setValue(Integer.toString(value));
					return;
				}
			}
		}
		throw new RuntimeException("Unable to set value of " + jdefinition.getValue() + " to " + value);
	}
}
