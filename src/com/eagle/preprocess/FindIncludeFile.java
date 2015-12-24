// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 20, 2015

package com.eagle.preprocess;

import java.io.IOException;

import com.eagle.parsers.EagleFileReader;

public interface FindIncludeFile
{
	public EagleFileReader findFile(String dir, String fname) throws IOException;
}
