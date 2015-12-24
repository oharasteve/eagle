// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.programmar.Template;

import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.tests.EagleInterpreter;

public class Template_Interpreter extends EagleInterpreter
{
	public Template_Interpreter(EagleSymbolTable symbolTable)
	{
		super(symbolTable);
	}
//	
//	@Override
//	public boolean isDefined(ReferenceInterface variable)
//	{
//		return false;
//	}
//
//	@Override
//	public int getIntValue(ReferenceInterface variable)
//	{
//		Template_Identifier_Definition def = (Template_Identifier_Definition) variable.getDefinition();
//		Template_DataStatement data = (Template_DataStatement) def.getParent();
//		int result = getIntValue(data.expr);
//		return result;
//	}
//	
//	@Override
//	public void setIntValue(DefinitionInterface variable, int value)
//	{
//		Template_Identifier_Definition def = (Template_Identifier_Definition) variable;
//		Template_DataStatement data = (Template_DataStatement) def.getParent();
//		String id = data.var.toString();
//		_symbolTable.setSymbol(id, null);
//	}
}
