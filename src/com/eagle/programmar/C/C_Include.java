// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 14, 2015

package com.eagle.programmar.C;

import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleInclude;
import com.eagle.programmar.CMacro.CMacro_Program;

public class C_Include extends EagleInclude
{
	public boolean preprocess(String[] file)
	{
		ParserManager parser = new ParserManager();
		CMacro_Program pgm = new CMacro_Program();
		if (!parser.parseStringArray(null, file, "", pgm))
		{
			return false;
		}
		return true;
	}
}
