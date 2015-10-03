// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Symbols;

import com.eagle.programmar.DefinitionInterface;

public class TCL_Namespace_Definition extends TCL_Identifier_Definition implements DefinitionInterface
{
	@Override
	public String typeName()
	{
		return "Namespace";
	}
}