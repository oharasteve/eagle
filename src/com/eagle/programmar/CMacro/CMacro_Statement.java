// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 14, 2015

package com.eagle.programmar.CMacro;

import com.eagle.preprocess.FindIncludeFile;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.tokens.TokenSequence;

public abstract class CMacro_Statement extends TokenSequence
{
	// Return true iff any changes were made.
	public abstract boolean processMacro(CMacro_Preprocess preprocessor, FindIncludeFile findInclude);
}
