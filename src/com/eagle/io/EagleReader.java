// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 1, 2016

package com.eagle.io;

import com.eagle.programmar.EagleLanguage;

public abstract class EagleReader
{
	public abstract EagleLanguage readFrom(String xmlFile) throws Exception;
}
