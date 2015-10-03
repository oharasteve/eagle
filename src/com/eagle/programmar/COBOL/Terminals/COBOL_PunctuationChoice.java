// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.EagleSyntax;
import com.eagle.tokens.TerminalPunctuationChoice;

public class COBOL_PunctuationChoice extends TerminalPunctuationChoice
{
	// Need default constructor for reading from the XML file
	public COBOL_PunctuationChoice()
	{
		super();
	}
	
	public COBOL_PunctuationChoice(String... puncts)
	{
		super(puncts);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		// Make sure a '*' is not a comment
		EagleLineReader rec = lines.get(_currentLine);
		if (rec.charAt(_currentChar) == '*')
		{
			EagleSyntax syntax = this.getSyntax();
			if (_currentChar == syntax._commentColumn) return false;
		}

		return super.parse(lines);
	}
}