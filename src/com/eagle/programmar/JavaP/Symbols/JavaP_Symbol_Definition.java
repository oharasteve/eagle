// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP.Symbols;

import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.tokens.DefinitionInterface;

public class JavaP_Symbol_Definition extends JavaP_Identifier implements DefinitionInterface
{
	@Override
	public String typeName()
	{
		return "Symbol";
	}
}