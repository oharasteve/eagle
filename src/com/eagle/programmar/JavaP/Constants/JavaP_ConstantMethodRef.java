// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 25, 2015

package com.eagle.programmar.JavaP.Constants;

import com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_ConstantShowable;
import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class JavaP_ConstantMethodRef extends TokenSequence implements JavaP_ConstantShowable
{
	public JavaP_KeywordChoice METHODREF = new JavaP_KeywordChoice(
			"Fieldref",
			"InterfaceMethodref",
			"Methodref");
	public JavaP_Symbol_Reference object;
	public PunctuationPeriod dot;
	public JavaP_Symbol_Reference field;
	
	@Override
	public String showConstant()
	{
		return object.showName() + " . " + field.showName();
	}
}
