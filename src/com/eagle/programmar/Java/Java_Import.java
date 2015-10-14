// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2015

package com.eagle.programmar.Java;

import java.util.HashMap;

import com.eagle.parsers.EagleFileReader;
import com.eagle.preprocess.EagleInclude;
import com.eagle.preprocess.FindIncludeFile;
import com.eagle.tokens.AbstractToken;

public class Java_Import extends EagleInclude
{
	public Java_Import(HashMap<String, AbstractToken> symbolTable)
	{
		super(symbolTable);
	}
	
	@Override
	public EagleFileReader preprocessFile(EagleFileReader lines, FindIncludeFile findInclude)
	{
		// TODO: implement
		return null;
	}

	@Override
	public void copyElement(AbstractToken token)
	{
		// TODO Auto-generated method stub
	}
}
