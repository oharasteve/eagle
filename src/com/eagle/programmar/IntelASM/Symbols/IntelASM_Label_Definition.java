// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Symbols;

import com.eagle.parsers.EagleFileReader;


public class IntelASM_Label_Definition extends IntelASM_Identifier_Definition
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		// Labels must be in column 1.
		if (lines.getCurrentChar() != 0) return false;
		return super.parse(lines);
	}

	@Override
	public String typeName()
	{
		return "Label";
	}
}