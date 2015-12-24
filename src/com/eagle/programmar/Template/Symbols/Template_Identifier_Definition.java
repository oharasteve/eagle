// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Symbols;

import com.eagle.programmar.Template.Terminals.Template_Identifier;
import com.eagle.tokens.DefinitionInterface;

public class Template_Identifier_Definition extends Template_Identifier implements DefinitionInterface
{
	@Override
	public String typeName()
	{
		return "Variable";
	}
}