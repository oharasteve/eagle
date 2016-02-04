// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2015

package com.eagle.programmar.COBOL;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleTracer;
import com.eagle.preprocess.EagleInclude;
import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.project.EagleProject;
import com.eagle.tokens.AbstractToken;

public class COBOL_CopyBook extends EagleInclude
{
	public COBOL_CopyBook(EagleProject project, EagleSymbolTable symbolTable, EagleTracer tracer)
	{
		super(project, symbolTable, tracer);
	}
	
	// TODO: Handle COBOL Copybooks with REPLACEMENT
	
	@Override
	public EagleFileReader preprocessFile(EagleFileReader lines)
	{
		return null;
	}

	@Override
	public void copyElement(AbstractToken token)
	{
	}
}
