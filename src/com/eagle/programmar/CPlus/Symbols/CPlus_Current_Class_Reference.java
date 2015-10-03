// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 13, 2015

package com.eagle.programmar.CPlus.Symbols;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.CPlus.CPlus_Class;
import com.eagle.tokens.AbstractToken;

public class CPlus_Current_Class_Reference extends C_Identifier_Reference
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (! super.parse(lines)) return false;
		
		// Find the containing CPlus_Class, the names must match exactly
		AbstractToken parent = getParent();
		while (parent != null)
		{
			if (parent instanceof CPlus_Class)
			{
				CPlus_Class parentClass = (CPlus_Class) parent;
				String parentClassName = parentClass.className.getValue();
				// Can you declare a constructor for an outer class? I don't think so.
				return parentClassName.equals(_id);
			}
			parent = parent.getParent();
		}
		return false;	// Wrong name -- doesn't match the class name
	}
}