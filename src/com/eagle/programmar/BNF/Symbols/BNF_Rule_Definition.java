// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF.Symbols;

import com.eagle.programmar.DefinitionInterface;
import com.eagle.programmar.BNF.Terminals.BNF_Identifier;

public class BNF_Rule_Definition extends BNF_Identifier implements DefinitionInterface
{
	@Override
	public String typeName()
	{
		return "Rule";
	}
}