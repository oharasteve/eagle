// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 27, 2011

package com.eagle.programmar.C.Symbols;

import com.eagle.programmar.DefinitionInterface;
import com.eagle.programmar.C.Terminals.C_Identifier;

public class C_Label_Definition extends C_Identifier implements DefinitionInterface
{
	@Override
	public String typeName()
	{
		return "Label";
	}
}