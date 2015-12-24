// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 25, 2015

package com.eagle.programmar.JavaP.Constants;

import com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_ConstantShowable;
import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_ConstantNameAndType extends TokenSequence implements JavaP_ConstantShowable
{
	public JavaP_Keyword NAMEANDTYPE = new JavaP_Keyword("NameAndType");
	public JavaP_Symbol_Reference name;
	public PunctuationColon colon;
	public JavaP_Symbol_Reference type;
	
	@Override
	public String showConstant()
	{
		return name.showName() + " : " + type.showName();
	}
}
