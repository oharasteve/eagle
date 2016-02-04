// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP.Constants;

import com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_ConstantShowable;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.tokens.TokenSequence;

public class JavaP_ConstantLong extends TokenSequence implements JavaP_ConstantShowable
{
	public JavaP_KeywordChoice type = new JavaP_KeywordChoice("double", "Double", "int", "Integer", "long", "Long");
	public JavaP_Number number;
	
	@Override
	public String showConstant()
	{
		return number.toString();
	}
}
