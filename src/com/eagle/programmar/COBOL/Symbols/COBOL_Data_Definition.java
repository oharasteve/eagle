// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Symbols;

import com.eagle.programmar.COBOL.COBOL_DataDeclaration;
import com.eagle.tokens.AbstractToken;


public class COBOL_Data_Definition extends COBOL_Identifier_Definition
{
	// Go all the way out to 01/05/etc level
	public COBOL_DataDeclaration getDeclaration()
	{
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof COBOL_DataDeclaration)
			{
				return (COBOL_DataDeclaration) parent;
			}
			parent = parent.getParent();
		}
		return null;	// Couldn't find it?
	}
	
	@Override
	public String typeName()
	{
		return "Data";
	}
}
