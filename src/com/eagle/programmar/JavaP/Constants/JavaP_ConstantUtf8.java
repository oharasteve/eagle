// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Constants;

import com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_ConstantShowable;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_RestOfLine;
import com.eagle.tokens.TokenSequence;

public class JavaP_ConstantUtf8 extends TokenSequence implements JavaP_ConstantShowable
{
	public JavaP_KeywordChoice UTF8 = new JavaP_KeywordChoice("Asciz", "Utf8");
	public @OPT JavaP_RestOfLine value;
		
	@Override
	public String showConstant()
	{
		return value.toString();
	}
}
