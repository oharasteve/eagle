// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2013

package com.eagle.programmar.Java;

import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.tests.EagleInterpreter;


public class Java_Interpreter extends EagleInterpreter
{
	public Java_Interpreter(EagleSymbolTable symbolTable)
	{
		super(symbolTable);
	}
	
//	@Override
//	public boolean isDefined(ReferenceInterface variable)
//	{
//		return false;
//	}
//
//	@Override
//	public int getIntValue(ReferenceInterface variable)
//	{
//		Java_Variable_Definition jdefinition = (Java_Variable_Definition) variable.getDefinition();
//		if (jdefinition.getParent() instanceof Java_Data)
//		{
//			Java_Data data = (Java_Data) jdefinition.getParent();
//			Java_DataInitialValue init = data.initialValue;
//			if (init != null)
//			{
//				Java_Expression expr = init.expression;
//				if (expr._whichToken instanceof Java_Number)
//				{
//					Java_Number num = (Java_Number) expr._whichToken;
//					return Integer.parseInt(num.getValue());
//				}
//			}
//		}
//		throw new RuntimeException("Unable to find a value for " + variable);
//	}
//	
//	@Override
//	public void setIntValue(DefinitionInterface definition, int value)
//	{
//		Java_Variable_Definition jdefinition = (Java_Variable_Definition) definition;
//		if (jdefinition.getParent() instanceof Java_Data)
//		{
//			Java_Data data = (Java_Data) jdefinition.getParent();
//			Java_DataInitialValue init = data.initialValue;
//			if (init != null)
//			{
//				Java_Expression expr = init.expression;
//				if (expr._whichToken instanceof Java_Number)
//				{
//					Java_Number num = (Java_Number) expr._whichToken;
//					num.setValue(Integer.toString(value));
//					return;
//				}
//			}
//		}
//		throw new RuntimeException("Unable to set value of " + jdefinition.getValue() + " to " + value);
//	}
}
