// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 13, 2015

package com.eagle.programmar.Java.Symbols;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Java.Java_Class;
import com.eagle.programmar.Java.Java_Enum;
import com.eagle.tokens.AbstractToken;

public class Java_Current_Class_Reference extends Java_Identifier_Reference
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (! super.parse(lines)) return false;
		
		// Find the containing Java_Class, the names must match exactly
		AbstractToken parent = getParent();
		while (parent != null)
		{
			if (parent instanceof Java_Class)
			{
				Java_Class parentClass = (Java_Class) parent;
				String parentClassName = parentClass.className.getValue();
				// Can you declare a constructor for an outer class? I don't think so.
				return parentClassName.equals(_id);
			}
			if (parent instanceof Java_Enum)
			{
				Java_Enum parentEnum = (Java_Enum) parent;
				String parentEnumName = parentEnum.id.getValue();
				// Can you declare a constructor for an outer class? I don't think so.
				return parentEnumName.equals(_id);
			}
			parent = parent.getParent();
		}
		return false;	// Wrong name -- doesn't match the class name
	}
}
