// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP.Symbols;

import com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_Constant;
import com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_ConstantShowable;
import com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.ReferenceInterface;

public class JavaP_Symbol_Reference extends JavaP_HashNumber implements ReferenceInterface
{
	public String showName()
	{
		JavaP_Symbol_Definition def = (JavaP_Symbol_Definition) getDefinition();
		AbstractToken parent = def.getParent();
		if (! (parent instanceof JavaP_Constant))
		{
			throw new RuntimeException("Expected " + def + " to have a parent of type JavaP_Constant, not " + parent);
		}
		JavaP_Constant constant = (JavaP_Constant) parent;
		AbstractToken whichConst = constant.type.getWhich();
		if (whichConst instanceof JavaP_ConstantShowable)
		{
			JavaP_ConstantShowable showable = (JavaP_ConstantShowable) whichConst;
			return showable.showConstant();
		}
		
		return def.toString();
	}
}
