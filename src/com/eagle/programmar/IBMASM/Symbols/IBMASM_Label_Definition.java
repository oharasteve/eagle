// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

package com.eagle.programmar.IBMASM.Symbols;

import com.eagle.parsers.EagleFileReader;

public class IBMASM_Label_Definition extends IBMASM_Identifier_Definition
{
	@Override
	public String typeName()
	{
		return "Label";
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		if (_currentChar != 0) return false;	// Labels must be in column 1
		return genericIdentifier(lines, ALPHAS, ALPHAS+DIGITS+"@", true);
	}
}