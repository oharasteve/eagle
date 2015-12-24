// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 14, 2015

package com.eagle.programmar.CMacro;

import com.eagle.preprocess.C.CMacro_Preprocess;

public interface CMacro_Processable
{
	// Return true iff any changes were made.
	public boolean processMacro(CMacro_Preprocess preprocessor);
}
