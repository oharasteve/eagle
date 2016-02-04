// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Constants;

import com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_ConstantShowable;
import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class JavaP_ConstantClass extends TokenSequence implements JavaP_ConstantShowable
{
	public JavaP_KeywordChoice CLASS = new JavaP_KeywordChoice("class", "Class");
	public JavaP_Symbol_Reference cls;
	
	@Override
	public String showConstant()
	{
		return cls.showName();
	}
}
